package com.fiap.food.api.assembler;

import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.api.product.dto.ProductResponse;
import com.fiap.food.core.model.ProductEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private ModelMapper modelMapper;
    PropertyMap<ProductRequest, ProductEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
        }
    };
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public ProductEntity toEntity(ProductRequest request) {

        ProductEntity productEntity = modelMapper.map(request, ProductEntity.class);
        return productEntity;
    }

    public ProductRequest toRequest(ProductEntity request) {

        ProductRequest productEntity = modelMapper.map(request, ProductRequest.class);
        return productEntity;
    }
    public ProductResponse toOutput(ProductEntity productEntity) {

        ProductResponse productResponse = modelMapper.map(productEntity, ProductResponse.class);
        return productResponse;
    }
}
