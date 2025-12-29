package com.restaurant.restaurant.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "category")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Customer extends BaseEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    private String phone;

    private String email;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;

    public List<OrderEntity> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }
}
