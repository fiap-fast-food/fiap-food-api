package com.fiap.food.api.assembler;

import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.api.order.dto.OrderRequest;
import com.fiap.food.api.order.dto.OrderResponse;
import com.fiap.food.core.model.CustomerEntity;
import com.fiap.food.core.model.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper;
    PropertyMap<CustomerRequest, CustomerEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
        }
    };
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public CustomerEntity toEntity(CustomerRequest request) {

        var customerEntity = modelMapper.map(request, CustomerEntity.class);
        return customerEntity;
    }
    public CustomerResponse toOutput(CustomerEntity customerEntity) {
        CustomerResponse customerResponse = modelMapper.map(customerEntity, CustomerResponse.class);
        return customerResponse;
    }

    public CustomerRequest toRequest(CustomerEntity customerEntity) {

        CustomerRequest customerRequest = modelMapper.map(customerEntity, CustomerRequest.class);
        return customerRequest;
    }

    public List<CustomerResponse> toResponseList(List<CustomerEntity> requests) {
        List<CustomerResponse> entities = new ArrayList<>();

        for (CustomerEntity request : requests) {
            CustomerResponse entity = toOutput(request);
            entities.add(entity);
        }

        return entities;
    }
}
