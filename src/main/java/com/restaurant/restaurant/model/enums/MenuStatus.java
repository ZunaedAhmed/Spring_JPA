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
public enum MenuStatus {
    ACTIVE("ACTIVE", "Menu Status: ACTIVE"),
    INACTIVE("INACTIVE", "Menu Status: INACTIVE");

    private static final Map<String, MenuStatus> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    MenuStatus::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static MenuStatus fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return INACTIVE;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), INACTIVE);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(MenuStatus::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
