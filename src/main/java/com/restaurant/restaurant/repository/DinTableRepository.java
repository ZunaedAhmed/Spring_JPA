package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.DinTable;
import org.springframework.stereotype.Repository;

@Repository
public interface DinTableRepository extends JpaRepository<DinTable, Long> {
}
