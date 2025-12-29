package com.restaurant.restaurant.validator;

import com.restaurant.restaurant.command.OrderCommand;
import com.restaurant.restaurant.entity.OrderEntity;
import com.restaurant.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class OrderValidator implements org.springframework.validation.Validator {

    private final OrderService orderService;

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderCommand command = (OrderCommand) target;
        if (command.getOrderEntity() == null) {
            errors.rejectValue(
                    "orderEntity",
                    "order.null",
                    "Order must not be null"
            );
            return;
        }
        OrderEntity order = command.getOrderEntity();
        if (order.getTotalAmount() == null || order.getTotalAmount().signum() < 0) {
            errors.rejectValue(
                    "orderEntity.totalAmount",
                    "order.totalAmount.invalid",
                    "Total amount must be zero or positive"
            );
        }
        // Add your business rule
        /*if (orderService.existsDuplicate(order)) {
            errors.reject(
                    "order.duplicate",
                    "Duplicate order detected"
            );
        }*/
    }
}

