package com.tranthien.watchstore.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.domain.Product_;

public class ProductSpecs {
    
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public static Specification<Product> minPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(Product_.PRICE), price);
    }

    public static Specification<Product> maxPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get(Product_.PRICE), price);
    }

    public static Specification<Product> matchPriceRange(double min, double max) {
        return (root, query, criteriaBulder) -> criteriaBulder.and(
            criteriaBulder.ge(root.get(Product_.PRICE), min),
            criteriaBulder.lt(root.get(Product_.PRICE), max)
        );
    }

    public static Specification<Product> matchMultiPriceRange(double min, double max) {
        return (root, query, criteriaBulder) -> criteriaBulder.between(root.get(Product_.PRICE), min, max);
    }

    public static Specification<Product> factoryEqual(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.FACTORY), factory);
    }

    public static Specification<Product> matchMultiFactory(List<String> factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factory);
    }

    public static Specification<Product> typeEqual(String type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.TYPE), type);
    } 

    public static Specification<Product> matchMultiType(List<String> type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.TYPE)).value(type);
    }
    

}
