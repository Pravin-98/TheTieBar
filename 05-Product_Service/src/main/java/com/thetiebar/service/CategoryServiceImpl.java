package com.thetiebar.service;

import java.util.List;
import java.util.stream.Collectors;

import com.thetiebar.constants.AppConstants;
import com.thetiebar.dto.CategoryDto;
import com.thetiebar.entity.Category;
import com.thetiebar.exception.ProductServiceException;
import com.thetiebar.mapper.CategoryMapper;
import com.thetiebar.repository.CategoryRepo;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepo categoryRepo;
	
	public CategoryServiceImpl(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = CategoryMapper.convertToEntity(categoryDto);
		Category savedCategory = categoryRepo.save(category);
		return CategoryMapper.converToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		Category existingCategory = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ProductServiceException(AppConstants.CTEGORY_NOT_FOUND,AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
		existingCategory.setCategoryName(categoryDto.getCategoryName());
		Category updatedCategory = categoryRepo.save(existingCategory);
		return CategoryMapper.converToDto(updatedCategory);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		return categories.stream().map(CategoryMapper::converToDto).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ProductServiceException(AppConstants.CTEGORY_NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
		return CategoryMapper.converToDto(category);
	}

	@Override
	public CategoryDto deleteCategoryById(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ProductServiceException(AppConstants.CTEGORY_NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
		categoryRepo.delete(category);
		return CategoryMapper.converToDto(category);
	}

}
