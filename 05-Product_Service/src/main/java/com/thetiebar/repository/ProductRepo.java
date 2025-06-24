package com.thetiebar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thetiebar.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
