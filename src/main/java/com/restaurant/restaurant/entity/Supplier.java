package com.restaurant.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "supplier")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Supplier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long supplierId;

    private String name;

    private String phone;

    private String email;

    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.LAZY
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
