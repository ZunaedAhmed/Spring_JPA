package com.restaurant.restaurant.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Setter
@Getter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private LocalDate orderDate;

    private LocalTime orderTime;

    private String orderType; // DINE_IN / TAKEOUT / DELIVERY

    private String status; // NEW / IN_KITCHEN / SERVED / PAID / CANCELED

    private BigDecimal totalAmount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private DinTable table;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

}
