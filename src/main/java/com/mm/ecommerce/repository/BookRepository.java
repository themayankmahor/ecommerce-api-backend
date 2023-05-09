package com.mm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
    
}
