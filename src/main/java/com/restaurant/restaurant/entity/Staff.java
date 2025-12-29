package com.restaurant.restaurant.entity;

import com.restaurant.restaurant.model.enums.StaffRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "staff")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Staff extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long staffId;

    private String name;

    @Enumerated(EnumType.STRING)
    private StaffRole role;

    @OneToMany(
            mappedBy = "staff",
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<OrderEntity> orders;

    public List<OrderEntity> getOrders() {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        return orders;
    }
}
