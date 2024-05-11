package com.fiap.food.api.category.service;

import com.fiap.food.api.category.dto.CategoryRequest;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CategoryEntity;

public interface CategoryService {
    CategoryEntity insert(CategoryRequest category);
    void update(CategoryRequest category) throws NotFoundException;
    CategoryEntity find(String categoryName) throws NotFoundException;
    CategoryEntity find(Long id) throws NotFoundException;
    void delete(Long id) throws NotFoundException;
}
