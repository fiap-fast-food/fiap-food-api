package com.fiap.food.api.order.dto;

import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.client.dto.PaymentRequestClientDTO;
import com.fiap.food.core.model.CustomerEntity;
import com.fiap.food.core.model.PaymentEntity;
import com.fiap.food.core.model.ProductEntity;
import com.fiap.food.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {

    private String cpfCustomer;
    private List<String> productsName;
    private CustomerRequest customer;
    private List<ProductRequest> products = new ArrayList<>();
    private LocalDateTime dateTimeOrder;
    private OrderStatus status;
    private String confirmationCode;
    private PaymentRequestClientDTO payment;
}
