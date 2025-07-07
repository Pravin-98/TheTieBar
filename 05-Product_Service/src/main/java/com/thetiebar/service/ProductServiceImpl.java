package com.thetiebar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thetiebar.constants.AppConstants;
import com.thetiebar.dto.ProductDto;
import com.thetiebar.entity.Product;
import com.thetiebar.exception.ProductServiceException;
import com.thetiebar.mapper.CategoryMapper;
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
		Product exstingProduct = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		exstingProduct.setName(productDto.getName());
		exstingProduct.setDescription(productDto.getDescription());
		exstingProduct.setPrice(productDto.getPrice());
		exstingProduct.setStock(productDto.getStock());
		exstingProduct.setImage(productDto.getImage());
		exstingProduct.setDiscount(productDto.getDiscount());
		exstingProduct.setPriceBeforeDiscount(productDto.getPriceBeforeDiscount());
		
		exstingProduct.setCategory(CategoryMapper.convertToEntity(productDto.getCategory()));
		
		Product updatedProduct = productRepo.save(exstingProduct);
		return ProductMapper.convertToDto(updatedProduct);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepo.findAll();
		return products.stream().map(ProductMapper:: convertToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(()-> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		return ProductMapper.convertToDto(product);
	}

	@Override
	public ProductDto deleteProductById(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(()-> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		productRepo.delete(product);
		
		return ProductMapper.convertToDto(product);
	}

	@Override
	public boolean updateStock(Integer productId, Integer quantity) {
		Product product = productRepo.findById(productId)
				.orElseThrow(()-> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		product.setStock(quantity);
		productRepo.save(product);
		return true;
	}

}
