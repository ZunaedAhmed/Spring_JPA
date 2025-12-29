package com.restaurant.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.restaurant.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}
