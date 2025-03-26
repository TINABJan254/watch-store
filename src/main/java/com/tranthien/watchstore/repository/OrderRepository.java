package com.tranthien.watchstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranthien.watchstore.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
