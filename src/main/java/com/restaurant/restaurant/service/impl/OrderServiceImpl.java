package com.restaurant.restaurant.service.impl;

import com.restaurant.restaurant.entity.Customer;
import com.restaurant.restaurant.entity.OrderEntity;
import com.restaurant.restaurant.entity.OrderItem;
import com.restaurant.restaurant.model.OrderFormModel;
import com.restaurant.restaurant.model.enums.OrderStatus;
import com.restaurant.restaurant.model.enums.OrderType;
import com.restaurant.restaurant.repository.CustomerRepository;
import com.restaurant.restaurant.repository.MenuItemRepository;
import com.restaurant.restaurant.repository.OrderRepository;
import com.restaurant.restaurant.service.DineService;
import com.restaurant.restaurant.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final DineService dineService;
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public OrderEntity doSave(OrderFormModel orderForm) {
        OrderEntity order = new OrderEntity();
        Customer customer = customerRepository
                .findById(orderForm.getCustomerId())
                .orElseGet(() -> {
                    Customer guest = new Customer();
                    guest.setName("GUEST");
                    return customerRepository.save(guest);
                });
        order.setCustomer(customer);
        if (orderForm.getTableId() != null) {
            dineService.findById(orderForm.getTableId())
                    .ifPresent(order::setTable);
        }
        OrderType type = OrderType.fromCode(orderForm.getOrderType());
        order.setOrderType(
                type != OrderType.CANCELED ? type : OrderType.DINE_IN
        );
        order.setStatus(OrderStatus.NEW);
        order.getItems().clear();
        BigDecimal total = BigDecimal.ZERO;
        if (orderForm.getItemIds() != null && !orderForm.getItemIds().isEmpty()) {
            for (int i = 0; i < orderForm.getItemIds().size(); i++) {
                Long menuItemId = Long.parseLong(orderForm.getItemIds().get(i));
                int quantity = (orderForm.getQuantities() != null &&
                        orderForm.getQuantities().size() > i)
                        ? orderForm.getQuantities().get(i)
                        : 1;
                if (quantity <= 0) continue;
                menuItemRepository.findById(menuItemId).ifPresent(menuItem -> {
                    OrderItem item = new OrderItem();
                    item.setMenuItem(menuItem);
                    item.setQuantity(quantity);
                    item.setUnitPrice(menuItem.getPrice());
                    BigDecimal lineTotal = menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));
                    item.setLineTotal(lineTotal);
                    order.addItem(item);
                });
            }
            total = order.getItems().stream()
                    .map(OrderItem::getLineTotal)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalAmount(total);
        }
        order.setOrderDate(
                Optional.ofNullable(order.getOrderDate())
                        .orElse(LocalDate.now())
        );
        order.setOrderTime(
                Optional.ofNullable(order.getOrderTime())
                        .orElse(LocalTime.now())
        );
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}