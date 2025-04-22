package com.tranthien.watchstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranthien.watchstore.domain.Cart;
import com.tranthien.watchstore.domain.CartDetail;
import com.tranthien.watchstore.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long>{
    CartDetail findByCartAndProduct(Cart cart, Product product);    
} 
