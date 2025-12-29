package com.restaurant.restaurant.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
@Data
@Setter
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;

}
