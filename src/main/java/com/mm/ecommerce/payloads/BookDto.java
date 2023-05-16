package com.mm.ecommerce.payloads;

import java.util.Date;

import lombok.Data;

@Data
public class BookDto {
    
    private int id;

    private String bookName;

    private String authorName;

    private String imageName;

    private long price;

    private String genres;

    private Date date;

}
