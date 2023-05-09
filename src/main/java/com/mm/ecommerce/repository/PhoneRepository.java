package com.mm.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.ecommerce.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    
    

}
