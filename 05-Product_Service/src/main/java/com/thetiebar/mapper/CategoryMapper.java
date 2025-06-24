package com.thetiebar.mapper;

import org.modelmapper.ModelMapper;

import com.thetiebar.dto.CategoryDto;
import com.thetiebar.entity.Category;

public class CategoryMapper {

	public static final ModelMapper mapper = new ModelMapper();
	
	public static CategoryDto converToDto(Category category) {
		return mapper.map(category, CategoryDto.class);
	}
	
	public static Category convertToEntity(CategoryDto categoryDto) {
		return mapper.map(categoryDto, Category.class);
	}
}
