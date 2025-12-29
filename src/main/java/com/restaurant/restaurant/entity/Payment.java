package com.restaurant.restaurant.entity;

import com.restaurant.restaurant.model.enums.PaymentMethod;
import com.restaurant.restaurant.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "payment")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity order;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime paidAt;

    void setOrder(OrderEntity order) {
        this.order = order;
    }
}
