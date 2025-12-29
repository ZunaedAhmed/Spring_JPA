package com.restaurant.restaurant.service.impl;

import com.restaurant.restaurant.entity.DinTable;
import com.restaurant.restaurant.repository.DinTableRepository;
import com.restaurant.restaurant.service.DineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DineServiceImpl implements DineService {
    private final DinTableRepository dinTableRepository;

    @Override
    public List<DinTable> findAll() {
        return dinTableRepository.findAll();

    }

    @Override
    public Optional<DinTable> findById(Long id) {
        return Optional.of(dinTableRepository.findById(id).orElse(null));
    }
}
