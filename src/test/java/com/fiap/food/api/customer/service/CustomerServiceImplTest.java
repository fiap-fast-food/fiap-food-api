package com.fiap.food.api.customer.service;

import com.fiap.food.api.AplicationConfigTest;
import com.fiap.food.api.assembler.CustomerMapper;
import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CustomerEntity;
import com.fiap.food.core.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("CustomerServiceImplTest")
class CustomerServiceImplTest extends AplicationConfigTest {

    @MockBean
    private CustomerMapper customerMapper;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    @DisplayName("mustSaveCustomer")
    public void mustSaveCustomer() {
        customerService.insert(getCustomerEntity());
        Mockito.verify(customerRepository, Mockito.times(1)).save(any(CustomerEntity.class));
    }

    @Test
    @DisplayName("mustUpdateCustomer")
    public void mustUpdateCustomer() throws NotFoundException {
        customerService.update(getCustomerEntity());
        Mockito.verify(customerRepository, Mockito.times(1)).save(any(CustomerEntity.class));
    }

    @Test
    @DisplayName("mustFindByIdCustomer")
    public void mustFindByIdCustomer() throws NotFoundException {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(getCustomerEntity()));
        customerService.findById(1L);
        Mockito.verify(customerRepository, Mockito.times(1)).findById(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("mustFindByCpfCustomer")
    public void mustFindByCpfCustomer() throws NotFoundException {
        Mockito.when(customerRepository.findByCpf(any())).thenReturn(Optional.of(getCustomerEntity()));
        customerService.findByCpf("888888888");
        Mockito.verify(customerRepository, Mockito.times(1)).findByCpf(ArgumentMatchers.any());
    }


    @Test
    @DisplayName("mustDeleteByIdCustomer")
    public void mustDeleteByIdCategory() throws NotFoundException {
        customerService.delete(1L);
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any());
    }


    private CustomerEntity getCustomerEntity() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("Teste");
        customerEntity.setCpf("00000000000");
        customerEntity.setEmail("teste@mail");
        return customerEntity;
    }

    private CustomerRequest getCustomerRequest() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCpf("00000000000");
        customerRequest.setEmail("teste@mail.com");
        customerRequest.setPassword("123456789");
        customerRequest.setName("teste");

        return customerRequest;
    }

}