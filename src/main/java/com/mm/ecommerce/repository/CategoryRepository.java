package com.mm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{


}
