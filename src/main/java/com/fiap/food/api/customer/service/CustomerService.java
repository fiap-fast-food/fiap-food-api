package com.fiap.food.api.customer.service;

import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CustomerEntity;

public interface CustomerService {
    void insert(CustomerEntity customer);
    void update(CustomerEntity customer) throws NotFoundException;
    CustomerEntity findById(Long id) throws NotFoundException;
    CustomerEntity findByCpf(String cpf) throws NotFoundException;
    void delete(Long id) throws NotFoundException;
}
