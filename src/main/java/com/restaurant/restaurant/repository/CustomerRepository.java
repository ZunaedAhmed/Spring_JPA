package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
