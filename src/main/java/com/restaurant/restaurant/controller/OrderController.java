package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entity.OrderEntity;
import com.restaurant.restaurant.model.OrderFormModel;
import com.restaurant.restaurant.repository.DinTableRepository;
import com.restaurant.restaurant.service.DineService;
import com.restaurant.restaurant.service.MenuItemService;
import com.restaurant.restaurant.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;
    private final MenuItemService menuItemService;
    private final DineService dineService;

    public OrderController(OrderService service, DinTableRepository tableRepository,
            MenuItemService menuItemService, DineService dineService) {
        this.service = service;

        // this.tableRepository = tableRepository;

        this.menuItemService = menuItemService;
        this.dineService = dineService;
    }

    @GetMapping
    public List<OrderEntity> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        OrderFormModel orderForm = new OrderFormModel();
        model.addAttribute("menuItems", menuItemService.findAll());
        model.addAttribute("orderForm", orderForm);
        model.addAttribute("tables", dineService.findAll());
        return "order_form";
    }

    @PostMapping("/new")
    public String createOrder(@ModelAttribute("orderForm") OrderFormModel orderForm) {

        service.doSave(orderForm);
        return "redirect:/";
    }
}
