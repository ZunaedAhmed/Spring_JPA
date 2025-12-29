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
public enum PaymentStatus {
    PENDING("PENDING", "Payment Status: PENDING"),
    COMPLETED("COMPLETED", "Payment Status: COMPLETED"),
    FAILED("FAILED", "Payment Status: FAILED");

    private static final Map<String, PaymentStatus> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    PaymentStatus::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static PaymentStatus fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return FAILED;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), FAILED);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(PaymentStatus::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
