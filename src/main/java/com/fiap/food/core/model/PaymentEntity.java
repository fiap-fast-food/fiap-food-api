package com.fiap.food.core.model;

import com.fiap.food.enums.StatusPaymentEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private StatusPaymentEnum statusPayment;

    @OneToOne(mappedBy = "payment")
    private OrderEntity order;

}
