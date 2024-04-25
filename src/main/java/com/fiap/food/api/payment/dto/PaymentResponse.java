package com.fiap.food.api.payment.dto;

import com.fiap.food.api.order.dto.OrderResponse;
import com.fiap.food.enums.StatusPaymentEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponse {
    private Long id;
    private BigDecimal amount;
    private StatusPaymentEnum statusPayment;
    private OrderResponse order;
}
