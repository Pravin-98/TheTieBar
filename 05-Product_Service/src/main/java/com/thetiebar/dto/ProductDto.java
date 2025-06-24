package com.thetiebar.dto;

import lombok.Data;

@Data
public class ProductDto {

	private Integer productId; 
	private String name;
	private String description;
	private Double price;
	private Integer stock;
	private String image;
	private Integer discount;
	private Double priceBeforeDiscount;

}
