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
public enum PaymentMethod {
    CASH("CASH", "Payment Method: CASH"),
    CARD("CARD", "Payment Method: CARD"),
    ONLINE("ONLINE", "Payment Method: ONLINE");

    private static final Map<String, PaymentMethod> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    PaymentMethod::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static PaymentMethod fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return ONLINE;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), ONLINE);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(PaymentMethod::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
