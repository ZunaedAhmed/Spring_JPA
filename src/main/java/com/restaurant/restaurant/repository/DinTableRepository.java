package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.DinTable;

public interface DinTableRepository extends JpaRepository<DinTable, Long> {
}
