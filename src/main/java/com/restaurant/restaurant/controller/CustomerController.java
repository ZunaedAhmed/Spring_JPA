package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Customer> get(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Customer> create(@RequestBody Customer c) {
        Customer saved = service.save(c);
        return ResponseEntity.created(URI.create("/api/customers/" + saved.getCustomerId())).body(saved);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public String customers(Model model) {
        model.addAttribute("customers", service.findAll());
        return "customers";
    }

    @GetMapping("/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_form";
    }

    @PostMapping("/new")
    public String createCustomer(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }
}
