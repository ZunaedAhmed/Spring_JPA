package com.restaurant.restaurant.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.restaurant.restaurant.entity.DinTable;
import com.restaurant.restaurant.repository.DinTableRepository;
import com.restaurant.restaurant.service.DineService;

@Service
public class DineServiceImpl implements DineService {
    private final DinTableRepository repository;

    public DineServiceImpl(DinTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DinTable> findAll() {
        return repository.findAll();

    }

    @Override
    public Optional<DinTable> findById(Long id) {
        return Optional.of(repository.findById(id).orElse(null));
    }

}
