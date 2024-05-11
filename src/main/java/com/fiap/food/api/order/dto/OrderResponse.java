package com.fiap.food.api.order.dto;

import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.api.product.dto.ProductResponse;
import com.fiap.food.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private CustomerResponse customer;
    private List<ProductResponse> products;
    private LocalDateTime dateTimeOrder;
    private OrderStatus status;
    private String confirmationCode;
}
