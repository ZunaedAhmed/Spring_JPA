package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.entity.DinTable;
import com.restaurant.restaurant.entity.MenuItem;
import com.restaurant.restaurant.repository.DinTableRepository;
import com.restaurant.restaurant.service.MenuItemService;
import com.restaurant.restaurant.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final MenuItemService menuItemService;
    private final CustomerService customerService;
    private final DinTableRepository tableRepository;

    public HomeController(MenuItemService menuItemService, CustomerService customerService,
            DinTableRepository tableRepository) {
        this.menuItemService = menuItemService;
        this.customerService = customerService;
        this.tableRepository = tableRepository;
    }

    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        List<MenuItem> menuItems = menuItemService.findAll();
        List<Customer> customers = customerService.findAll();
        List<DinTable> tables = tableRepository.findAll();
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("customers", customers);
        model.addAttribute("tables", tables);
        return "index";
    }
}
