package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
