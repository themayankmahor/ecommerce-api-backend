package com.mm.ecommerce.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {
    
    private int categoryId;

    @NotBlank
    @Size(min = 2, message = "Min size of  category title is 2")
    private String categoryTitle;

    @NotBlank
    @Size(min = 4, message = "Min size of description is 4")
    private String categoryDescription;

}
