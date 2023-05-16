package com.mm.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.CartItem;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<CartItem, Integer>{
    
    CartItem findByUserAndProduct(User user, Product product);

    List<CartItem> findByUser(User user);
}
