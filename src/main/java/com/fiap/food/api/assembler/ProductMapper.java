package com.fiap.food.api.assembler;

import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.api.product.dto.ProductResponse;
import com.fiap.food.core.model.ProductEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;
    PropertyMap<ProductRequest, ProductEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
            // TODO document why this method is empty
        }
    };
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public ProductEntity toEntity(ProductRequest request) {

        return modelMapper.map(request, ProductEntity.class);
    }

    public ProductRequest toRequest(ProductEntity request) {

        return modelMapper.map(request, ProductRequest.class);
    }
    public ProductResponse toOutput(ProductEntity productEntity) {

        return modelMapper.map(productEntity, ProductResponse.class);
    }
}
