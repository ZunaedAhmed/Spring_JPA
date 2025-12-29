package com.restaurant.restaurant.helper;

import com.restaurant.restaurant.model.OrderFormModel;
import com.restaurant.restaurant.service.DineService;
import com.restaurant.restaurant.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class OrderHelper {
    private final MenuItemService menuItemService;
    private final DineService dineService;

    public void newOrder(Model model, OrderFormModel orderForm) {
        model.addAttribute("menuItems", menuItemService.findAll());
        model.addAttribute("orderForm", orderForm);
        model.addAttribute("tables", dineService.findAll());
    }

    public void createOrder(Model model) {
        model.addAttribute("menuItems", menuItemService.findAll());
        model.addAttribute("tables", dineService.findAll());
    }
}
