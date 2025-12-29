package com.restaurant.restaurant.entity;

import com.restaurant.restaurant.model.enums.MenuStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "menu_item")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class MenuItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long menuItemId;

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "menu_item_inventory",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "inventory_item_id")
    )
    @ToString.Exclude
    private List<InventoryItem> inventory;

    public List<InventoryItem> getInventory() {
        if (inventory == null) {
            inventory = new ArrayList<>();
        }
        return inventory;
    }
}
