package com.mm.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Role {
    
    @Id
    private int id;

    private String name;

}
