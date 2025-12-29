package com.restaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import com.restaurant.restaurant.entity.OrderEntity;
import com.restaurant.restaurant.model.OrderFormModel;

public interface OrderService {

    List<OrderEntity> findAll();

    Optional<OrderEntity> findById(Long id);

    OrderEntity doSave(OrderFormModel orderForm);

    void deleteById(Long id);

}
