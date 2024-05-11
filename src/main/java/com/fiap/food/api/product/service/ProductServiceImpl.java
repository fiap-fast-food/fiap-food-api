package com.fiap.food.api.product.service;

import com.fiap.food.api.assembler.ProductMapper;
import com.fiap.food.api.category.service.CategoryService;
import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.ProductEntity;
import com.fiap.food.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    @Override
    public ProductEntity insert(ProductRequest product) throws NotFoundException {
        var productEntity = productMapper.toEntity(product);
        var category = categoryService.find(product.getNameCategory());
        productEntity.setCategory(category);
        return productRepository.save(productEntity);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(ProductEntity product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductEntity> findByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public ProductEntity findById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public ProductEntity findByProductName(String productName) throws NotFoundException {
        return productRepository.findByName(productName).orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
