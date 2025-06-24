package com.thetiebar.mapper;


import org.springframework.stereotype.Component;

import com.thetiebar.dto.ProductDto;
import com.thetiebar.entity.Product;

import org.modelmapper.ModelMapper;

@Component
public class ProductMapper {

	private static final ModelMapper mapper = new ModelMapper();
	
	public static ProductDto convertToDto(Product product) {
		return mapper.map(product, ProductDto.class);
	}
	
	public static Product convertToEntity(ProductDto productDto) {
		return mapper.map(productDto, Product.class);
	}
}
