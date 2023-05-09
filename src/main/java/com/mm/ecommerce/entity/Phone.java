package com.mm.ecommerce.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String phoneName;

    private String brandName;

    private long price;

    private String ram;

    private String rom;

    private String camera;

    private Date date;

}
