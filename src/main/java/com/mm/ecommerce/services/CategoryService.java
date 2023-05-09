package com.mm.ecommerce.services;

import java.util.List;

import com.mm.ecommerce.payloads.CategoryDto;

public interface CategoryService {
    
    ///Create
    CategoryDto createCategory(CategoryDto categoryDto);

    ///Get
    CategoryDto getCategorySingle(int categoryId);

    ///Get All post
    List<CategoryDto> getAllCategories();

    ///Update
    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

    ///Delete Category
    void deleteCategory(int categoryId);

}
