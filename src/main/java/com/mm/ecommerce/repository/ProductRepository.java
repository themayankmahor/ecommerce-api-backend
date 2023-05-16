package com.mm.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Category;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    
    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findBySeller(User seller, Pageable pageable);

    Page<Product> findBySellerAndCategory(User seller, Category category, Pageable pageable);
    
    Product findByProductName(String productName);

}
