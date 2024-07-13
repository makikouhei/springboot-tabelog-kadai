package com.example.nagoyamesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {
	List<Category> findByNameContaining(String name);
}
