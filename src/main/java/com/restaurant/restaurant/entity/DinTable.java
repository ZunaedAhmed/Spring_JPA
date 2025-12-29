package com.restaurant.restaurant.entity;

import com.restaurant.restaurant.model.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "din_table")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DinTable extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long tableId;

    private Integer seats;
    private String location;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    @OneToMany(
            mappedBy = "table",
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
