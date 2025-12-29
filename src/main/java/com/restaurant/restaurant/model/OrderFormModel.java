package com.restaurant.restaurant.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class OrderFormModel extends BaseFormModel {
    private Long customerId;
    private Long staffId;
    private Long tableId;
    private List<String> itemIds;
    private String orderType;
    private Long manuOrderId;
    private List<Integer> quantities;
}
