package com.restaurant.restaurant.service;

import com.restaurant.restaurant.entity.DinTable;

import java.util.List;
import java.util.Optional;

public interface DineService {

    List<DinTable> findAll();

    Optional<DinTable> findById(Long id);
}
