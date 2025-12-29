package com.restaurant.restaurant.model;

import java.util.List;

import lombok.Data;

@Data
public class OrderFormModel {
    private Long customerId;
    private Long staffId;
    private Long tableId;
    private List<String> itemIds;
    private String orderType;
    private Long manuOrderId;
    private List<Integer> quantities;

}
