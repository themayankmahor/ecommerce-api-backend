package com.mm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
    
}
