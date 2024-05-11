package com.fiap.food.api.assembler;

import com.fiap.food.api.order.dto.OrderRequest;
import com.fiap.food.api.order.dto.OrderResponse;
import com.fiap.food.core.model.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;
    PropertyMap<OrderRequest, OrderEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
            // TODO document why this method is empty
        }
    };
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public OrderEntity toEntity(OrderRequest request) {

        return modelMapper.map(request, OrderEntity.class);
    }
    public OrderResponse toOutput(OrderEntity orderEntity) {

        return modelMapper.map(orderEntity, OrderResponse.class);
    }

    public List<OrderResponse> toResponseList(List<OrderEntity> requests) {
        List<OrderResponse> entities = new ArrayList<>();

        for (OrderEntity request : requests) {
            OrderResponse entity = toOutput(request);
            entities.add(entity);
        }

        return entities;
    }
}
