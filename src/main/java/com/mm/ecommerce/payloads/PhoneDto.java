package com.mm.ecommerce.payloads;

import java.util.Date;

import lombok.Data;

@Data
public class PhoneDto {
    
    private int id;

    private String phoneName;

    private String brandName;

    private long price;

    private String ram;

    private String rom;

    private String camera;

    private Date date;

}
