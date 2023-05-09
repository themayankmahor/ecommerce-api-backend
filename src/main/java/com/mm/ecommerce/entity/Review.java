package com.mm.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private Product product;
}
