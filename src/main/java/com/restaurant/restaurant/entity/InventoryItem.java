package com.restaurant.restaurant.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_item")
@Data
@Setter
@Getter
@NoArgsConstructor
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryItemId;

    private String name;

    private String unit;

    private BigDecimal stockQty;

    private BigDecimal reorderLevel;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(mappedBy = "inventory")
    private List<MenuItem> menuItems = new ArrayList<>();

}
