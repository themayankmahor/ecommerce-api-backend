package com.mm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
