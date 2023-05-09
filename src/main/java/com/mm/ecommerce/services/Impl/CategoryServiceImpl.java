package com.mm.ecommerce.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.Category;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.payloads.CategoryDto;
import com.mm.ecommerce.repository.CategoryRepository;
import com.mm.ecommerce.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    ///Create Category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        
        Category category = modelMapper.map(categoryDto, Category.class);
        
        Category savedCategory =  categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);

    }


    ///Get Single Category
    @Override
    public CategoryDto getCategorySingle(int categoryId) {
        
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        return modelMapper.map(category, CategoryDto.class);
    }

    ///Get All Categories
    @Override
    public List<CategoryDto> getAllCategories() {
        
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }

    ///Update Category
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryDto.class);

    }

    ///Delete Category
    @Override
    public void deleteCategory(int categoryId) {
        
        Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));

		categoryRepository.delete(category);
    }
    
}
