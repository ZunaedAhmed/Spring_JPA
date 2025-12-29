package com.restaurant.restaurant.service.impl;

import com.restaurant.restaurant.entity.MenuItem;
import com.restaurant.restaurant.repository.MenuItemRepository;
import com.restaurant.restaurant.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repository;

    public MenuItemServiceImpl(MenuItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MenuItem> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MenuItem> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return repository.save(menuItem);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
