package com.restaurant.restaurant.service.impl;

import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.entity.OrderEntity;
import com.restaurant.restaurant.entity.OrderItem;
import com.restaurant.restaurant.model.OrderFormModel;
import com.restaurant.restaurant.repository.CustomerRepository;
import com.restaurant.restaurant.repository.MenuItemRepository;
import com.restaurant.restaurant.repository.OrderRepository;
import com.restaurant.restaurant.service.DineService;
import com.restaurant.restaurant.service.OrderService;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CustomerRepository customerRepository;
    private final DineService dineService;
    private final MenuItemRepository menuItemRepository;

    public OrderServiceImpl(OrderRepository repository, CustomerRepository customerRepository,
            DineService dineService, MenuItemRepository menuItemRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.dineService = dineService;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> orders = repository.findAll();
        return orders;
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public OrderEntity doSave(OrderFormModel orderForm) {
        OrderEntity order = new OrderEntity();
        Customer customer = customerRepository.findById(orderForm.getCustomerId())
                .orElseGet(() -> {
                    Customer newCustomer = new Customer();
                    // newCustomer.setCustomerId(orderForm.getCustomerId());
                    newCustomer.setName("GUEST");
                    return newCustomer;
                });
        order.setCustomer(customer);
        if (orderForm.getTableId() != null)
            dineService.findById(orderForm.getTableId()).ifPresent(order::setTable);
        order.setOrderType(orderForm.getOrderType());
        order.setStatus("NEW");

        BigDecimal total = BigDecimal.ZERO;
        if (orderForm.getItemIds() != null && !orderForm.getItemIds().isEmpty()) {
            for (int i = 0; i < orderForm.getItemIds().size(); i++) {
                Long mid = Long.parseLong(orderForm.getItemIds().get(i));
                Integer qty = (orderForm.getQuantities() != null && orderForm.getQuantities().size() > i)
                        ? orderForm.getQuantities().get(i)
                        : 1;
                menuItemRepository.findById(mid).ifPresent(menuItem -> {
                    OrderItem oi = new OrderItem();
                    oi.setMenuItem(menuItem);
                    oi.setQuantity(qty);
                    oi.setUnitPrice(menuItem.getPrice());
                    BigDecimal line = menuItem.getPrice().multiply(BigDecimal.valueOf(qty));
                    oi.setLineTotal(line);
                    oi.setOrder(order);
                    order.getItems().add(oi);
                });
            }
            for (OrderItem it : order.getItems()) {
                total = total.add(it.getLineTotal() != null ? it.getLineTotal() : BigDecimal.ZERO);
            }
            order.setTotalAmount(total);
        }

        if (order.getOrderDate() == null)
            order.setOrderDate(LocalDate.now());
        if (order.getOrderTime() == null)
            order.setOrderTime(LocalTime.now());
        // Note: further business rules (totals, stock checks) should be implemented in
        // service layer
        return repository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}