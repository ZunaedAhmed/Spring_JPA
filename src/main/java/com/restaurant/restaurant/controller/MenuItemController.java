package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entity.MenuItem;
import com.restaurant.restaurant.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/menu-items")
public class MenuItemController {
    private final MenuItemService service;

    @GetMapping
    public List<MenuItem> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem menuItem) {
        MenuItem saved = service.save(menuItem);
        return ResponseEntity.created(URI.create("/api/menu-items/" + saved.getMenuItemId())).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
