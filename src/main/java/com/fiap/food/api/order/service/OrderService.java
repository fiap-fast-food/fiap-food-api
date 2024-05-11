package com.fiap.food.api.order.service;

import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.OrderEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> findAll();
    OrderEntity findByConfirmationCode(String confirmationCode) throws NotFoundException;
    void insert(String cpfCustomer, List<String> productsName) throws NotFoundException;
    void updateConfirmOrder(OrderEntity order) throws NotFoundException;

    void putStatusOrderByConfirmationCodeAndStatus(String confirmationCode, String status) throws NotFoundException;
}
