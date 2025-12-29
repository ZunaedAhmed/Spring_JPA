package com.restaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import com.restaurant.restaurant.entity.DinTable;

public interface DineService {

    List<DinTable> findAll();

    Optional<DinTable> findById(Long id);

}
