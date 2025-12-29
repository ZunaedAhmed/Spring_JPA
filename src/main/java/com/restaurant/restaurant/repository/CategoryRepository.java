package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
