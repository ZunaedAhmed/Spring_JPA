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
public enum StaffRole {
    WAITER("WAITER", "Staff Role: WAITER"),
    CHEF("CHEF", "Staff Role: CHEF"),
    MANAGER("MANAGER", "Staff Role: MANAGER");

    private static final Map<String, StaffRole> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    StaffRole::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static StaffRole fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return WAITER;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), WAITER);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(StaffRole::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
