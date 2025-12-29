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
public class Category extends BaseEntity {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<MenuItem> items;

    public List<MenuItem> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }
}
