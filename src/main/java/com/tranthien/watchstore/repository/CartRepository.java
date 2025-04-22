package com.tranthien.watchstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranthien.watchstore.domain.Cart;
import com.tranthien.watchstore.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    Cart findByUser(User user);
}
