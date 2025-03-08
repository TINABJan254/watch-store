package com.tranthien.watchstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranthien.watchstore.domain.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
