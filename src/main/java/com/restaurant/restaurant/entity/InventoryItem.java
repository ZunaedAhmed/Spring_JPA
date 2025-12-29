package com.restaurant.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "inventory_item")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class InventoryItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long inventoryItemId;

    private String name;

    private String unit;

    private BigDecimal stockQty;

    private BigDecimal reorderLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(
            mappedBy = "inventory",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<MenuItem> menuItems;

    public List<MenuItem> getMenuItems() {
        if (menuItems == null) {
            menuItems = new ArrayList<>();
        }
        return menuItems;
    }
}
