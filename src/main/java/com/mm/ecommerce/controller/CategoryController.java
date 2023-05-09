package com.mm.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.payloads.ApiResponse;
import com.mm.ecommerce.payloads.CategoryDto;
import com.mm.ecommerce.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    

    ///Create Category
    @PostMapping("/new-category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);

        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    ///Get single Category
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId)
    {
        CategoryDto categoryDto = categoryService.getCategorySingle(categoryId);

        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    ///Get All categories
    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryDto>> getCategories()
    {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    ///Update Category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") int categoryId)
    {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    ///Delete Category
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int cacategoryId)
    {
        categoryService.deleteCategory(cacategoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
    }

}
