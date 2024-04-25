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
    private ModelMapper modelMapper;
    PropertyMap<OrderRequest, OrderEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
        }
    };
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public OrderEntity toEntity(OrderRequest request) {

        OrderEntity orderEntity = modelMapper.map(request, OrderEntity.class);
        return orderEntity;
    }
    public OrderResponse toOutput(OrderEntity orderEntity) {

        OrderResponse orderResponse = modelMapper.map(orderEntity, OrderResponse.class);
        return orderResponse;
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
