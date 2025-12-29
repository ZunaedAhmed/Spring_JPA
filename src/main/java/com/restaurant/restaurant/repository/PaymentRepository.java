package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
