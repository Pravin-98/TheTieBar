package com.thetiebar.service;

import java.util.List;

import com.thetiebar.dto.CategoryDto;

public interface CategoryService {

	public CategoryDto addCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto);
	
	public List<CategoryDto> getAllCategory();
	
	public CategoryDto getCategoryById(Integer categoryId);
	
	public CategoryDto deleteCategoryById(Integer categoryId);
}
