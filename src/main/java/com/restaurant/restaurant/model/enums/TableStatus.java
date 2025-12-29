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
public enum TableStatus {
    AVAILABLE("AVAILABLE", "Table Status: AVAILABLE"),
    OCCUPIED("OCCUPIED", "Table Status: OCCUPIED"),
    RESERVED("RESERVED", "Table Status: RESERVED");

    private static final Map<String, TableStatus> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    TableStatus::getCode,
                    Function.identity()));
    private final String code;
    private final String description;

    public static TableStatus fromCode(String code) {
        if (isNull(code) || code.isBlank()) {
            return OCCUPIED;
        }
        return LOOKUP.getOrDefault(code.trim().toUpperCase(), OCCUPIED);
    }

    public static String[] getAllCodes() {
        return Arrays.stream(values()).map(TableStatus::getCode).toArray(String[]::new);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, description);
    }
}
