package com.fiap.food.api.product.service;

import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.api.product.dto.ProductResponse;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductEntity insert(ProductRequest productRequest) throws NotFoundException;
    void delete(Long id);
    void update(ProductEntity productEntity);
    List<ProductEntity> findByCategoryName(String categoryName);
    ProductEntity findById(Long id) throws NotFoundException;
    ProductEntity findByProductName(String productName) throws NotFoundException;
}
