package com.fiap.food.api.assembler;

import com.fiap.food.api.category.dto.CategoryRequest;
import com.fiap.food.api.category.dto.CategoryResponse;
import com.fiap.food.core.model.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private ModelMapper modelMapper;
    PropertyMap<CategoryRequest, CategoryEntity> skipModifiedFieldsMap = new PropertyMap<>() {
        protected void configure() {
        }
    };
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(skipModifiedFieldsMap);
    }
    public CategoryEntity toEntity(CategoryRequest request) {

        CategoryEntity category = modelMapper.map(request, CategoryEntity.class);
        return category;
    }
    public CategoryResponse toOutput(CategoryEntity categoryEntity) {

        CategoryResponse categoryResponse = modelMapper.map(categoryEntity, CategoryResponse.class);
        return categoryResponse;
    }
}
