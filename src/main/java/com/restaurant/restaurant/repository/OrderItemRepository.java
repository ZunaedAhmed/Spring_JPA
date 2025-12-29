package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
