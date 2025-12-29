package com.restaurant.restaurant.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    NEW("NEW", "Order Status: NEW"),
    IN_KITCHEN("IN_KITCHEN", "Order Status: IN_KITCHEN"),
    SERVED("SERVED", "Order Status: SERVED"),
    PAID("PAID", "Order Status: PAID"),
    CANCELED("CANCELED", "Order Status: CANCELED");

    private static final Map<String, OrderStatus> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    OrderStatus::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static OrderStatus fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return CANCELED;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), CANCELED);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(OrderStatus::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
