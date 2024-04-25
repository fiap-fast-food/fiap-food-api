package com.fiap.food.api.customer.service;

import com.fiap.food.api.assembler.CustomerMapper;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CustomerEntity;
import com.fiap.food.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerEntityMapper;
    @Override
    public void insert(CustomerEntity customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(CustomerEntity customer) throws NotFoundException {
        customerRepository.save(customer);
    }

    @Override
    public CustomerEntity findById(Long id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    public CustomerEntity findByCpf(String cpf) throws NotFoundException {
       return customerRepository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Customer not Found"));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        customerRepository.deleteById(id);
    }
}
