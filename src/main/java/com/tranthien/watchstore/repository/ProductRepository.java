package com.tranthien.watchstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tranthien.watchstore.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{
    Page<Product> findAll(Pageable page);
    Page<Product> findAll(Specification<Product> specification, Pageable page);

    @Query(value = """
            SELECT p.id, p.name, p.price, p.factory, SUM(od.quantity) AS soldQuantity
            FROM order_detail od
            JOIN products p ON od.product_id = p.id
            JOIN orders o ON od.order_id = o.id
            WHERE o.status = 'COMPLETE'
            GROUP BY p.id, p.name, p.price, p.factory
            ORDER BY soldQuantity DESC
        """, 
            countQuery = """
            SELECT COUNT(*) FROM (
                SELECT 1
                FROM order_detail od
                JOIN products p ON od.product_id = p.id
                JOIN orders o ON od.order_id = o.id
                WHERE o.status = 'COMPLETE'
                GROUP BY p.id, p.name, p.price, p.factory
            ) AS count_table
        """,
            nativeQuery = true)
    Page<Object[]> findTopSellingProducts(Pageable pageable);

    @Query(value = """
            SELECT p.id, p.name, p.price, p.factory, SUM(od.quantity) AS soldQuantity
            FROM products p
            LEFT JOIN order_detail od ON od.product_id = p.id
            LEFT JOIN orders o ON od.order_id = o.id and o.status = 'COMPLETE'
            GROUP BY p.id, p.name, p.price, p.factory
            ORDER BY p.id ASC
        """, 
            countQuery = """
            SELECT COUNT(*) FROM (
                SELECT 1
                FROM products p
                LEFT JOIN order_detail od ON od.product_id = p.id
                LEFT JOIN orders o ON od.order_id = o.id and o.status = 'COMPLETE'
                GROUP BY p.id, p.name, p.price, p.factory
            ) AS count_table
        """,    
        nativeQuery = true)
    Page<Object[]> fetchProductWithNativeSQL(Pageable pageable);

}
