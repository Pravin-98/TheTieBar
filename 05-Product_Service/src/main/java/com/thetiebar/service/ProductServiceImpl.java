package com.thetiebar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thetiebar.dto.ProductDto;
import com.thetiebar.entity.Product;
import com.thetiebar.mapper.ProductMapper;
import com.thetiebar.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		
		Product product = ProductMapper.convertToEntity(productDto);
		Product saveProduct = productRepo.save(product);
		return ProductMapper.convertToDto(saveProduct);
	}

	@Override
	public ProductDto updateProduct(Integer productId, ProductDto productDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto deleteProductById(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStock(Integer productId, Integer quantity) {
		// TODO Auto-generated method stub
		return false;
	}

}
