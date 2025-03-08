package com.tranthien.watchstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranthien.watchstore.domain.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

} 
