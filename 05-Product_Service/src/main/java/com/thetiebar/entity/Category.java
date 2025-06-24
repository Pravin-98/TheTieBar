package com.thetiebar.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE)
	private Integer categoryId;
	
	private String categoryName;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product> products;
	
}
