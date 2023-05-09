package com.mm.ecommerce.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    
    private int productId;

    private String productName;

    private long price;

    private String imageName;
 
    private Date date;

    private UserDto seller;

    private CategoryDto category;

    private Set<ReviewDto> reviews = new HashSet<>();

}
