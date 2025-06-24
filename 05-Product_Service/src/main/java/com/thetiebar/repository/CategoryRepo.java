package com.thetiebar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thetiebar.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
