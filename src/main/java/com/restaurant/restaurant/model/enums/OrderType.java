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
public enum OrderType {
    DINE_IN("DINE_IN", "Order Type: DINE_IN"),
    TAKEOUT("TAKEOUT", "Order Type: TAKEOUT"),
    DELIVERY("DELIVERY", "Order Type: DELIVERY"),
    CANCELED("CANCELED", "Order Type: CANCELED");

    private static final Map<String, OrderType> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    OrderType::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static OrderType fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return CANCELED;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), CANCELED);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(OrderType::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
