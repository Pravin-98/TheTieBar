package com.thetiebar.service;

import java.util.List;

import com.thetiebar.dto.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto productDto);
	
	public ProductDto updateProduct(Integer productId, ProductDto productDto);
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto getProductById(Integer productId);
	
	public ProductDto deleteProductById(Integer productId);
	
	public boolean updateStock(Integer productId, Integer quantity);
}
