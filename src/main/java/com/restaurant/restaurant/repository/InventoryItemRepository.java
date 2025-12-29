package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
