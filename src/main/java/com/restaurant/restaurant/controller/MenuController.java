package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.service.MenuItemService;
import com.restaurant.restaurant.entity.MenuItem;
import com.restaurant.restaurant.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuController {

    private final MenuItemService menuItemService;
    private final CategoryRepository categoryRepository;

    public MenuController(MenuItemService menuItemService, CategoryRepository categoryRepository) {
        this.menuItemService = menuItemService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("menuItems", menuItemService.findAll());
        return "menu";
    }

    @GetMapping("/new")
    public String newMenuItemForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("categories", categoryRepository.findAll());
        return "menu_item_form";
    }

    @PostMapping("/new")
    public String createMenuItem(@ModelAttribute MenuItem menuItem, @RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            categoryRepository.findById(categoryId).ifPresent(menuItem::setCategory);
        }
        menuItemService.save(menuItem);
        return "redirect:/menu";
    }
}
