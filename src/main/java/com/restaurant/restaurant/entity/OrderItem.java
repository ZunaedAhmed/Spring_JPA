package com.restaurant.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "order_item")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuItem menuItem;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal lineTotal;

    void setOrder(OrderEntity order) {
        this.order = order;
    }
}
