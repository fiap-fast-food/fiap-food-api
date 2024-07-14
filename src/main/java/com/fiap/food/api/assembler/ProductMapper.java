package com.fiap.food.api.assembler;

import com.fiap.food.client.dto.ProductRequestClientDTO;
import com.fiap.food.client.dto.ProductResponseClientDTO;
import com.fiap.food.core.model.ProductEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;
    PropertyMap<ProductRequestClientDTO, ProductEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
            // TODO document why this method is empty
        }
    };
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public ProductEntity toEntity(ProductRequestClientDTO request) {

        return modelMapper.map(request, ProductEntity.class);
    }

    public ProductRequestClientDTO toRequest(ProductRequestClientDTO request) {

        return modelMapper.map(request, ProductRequestClientDTO.class);
    }
    public ProductResponseClientDTO toOutput(ProductEntity productEntity) {

        return modelMapper.map(productEntity, ProductResponseClientDTO.class);
    }
}
