package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
