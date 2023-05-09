package com.mm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
