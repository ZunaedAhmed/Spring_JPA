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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_item")
@Data
@Setter
@Getter
@NoArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuItemId;

    private String name;

    private BigDecimal price;

    private String status; // ACTIVE / INACTIVE

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(name = "menu_item_inventory", joinColumns = @JoinColumn(name = "menu_item_id"), inverseJoinColumns = @JoinColumn(name = "inventory_item_id"))
    private List<InventoryItem> inventory = new ArrayList<>();

}
