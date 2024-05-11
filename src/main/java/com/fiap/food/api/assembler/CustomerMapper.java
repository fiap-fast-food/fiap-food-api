package com.fiap.food.api.assembler;

import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.core.model.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;
    PropertyMap<CustomerRequest, CustomerEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
            // TODO document why this method is empty
        }
    };
    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public CustomerEntity toEntity(CustomerRequest request) {

        return modelMapper.map(request, CustomerEntity.class);
    }
    public CustomerResponse toOutput(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerResponse.class);
    }

    public CustomerRequest toRequest(CustomerEntity customerEntity) {

        return modelMapper.map(customerEntity, CustomerRequest.class);
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
