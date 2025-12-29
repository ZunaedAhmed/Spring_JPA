package com.restaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import com.restaurant.restaurant.entity.MenuItem;

public interface MenuItemService {
    List<MenuItem> findAll();

    Optional<MenuItem> findById(Long id);

    MenuItem save(MenuItem menuItem);

    void deleteById(Long id);
}
