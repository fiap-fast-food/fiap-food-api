package com.fiap.food.api.product.service;

import com.fiap.food.api.AplicationConfigTest;
import com.fiap.food.api.assembler.CategoryMapper;
import com.fiap.food.api.assembler.ProductMapper;
import com.fiap.food.api.category.service.CategoryService;
import com.fiap.food.client.dto.ProductRequestClientDTO;
import com.fiap.food.client.service.ProductClient;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.CategoryEntity;
import com.fiap.food.core.model.ProductEntity;
import com.fiap.food.core.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("ProductServiceImplTest")
class ProductServiceImplTest extends AplicationConfigTest {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private ProductMapper productMapper;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductClient productClient;

    @Test
    @DisplayName("mustFindByProductNameProduct")
    public void mustFindByProductNameProduct() throws NotFoundException {
        Mockito.when(productRepository.findByName(any())).thenReturn(Optional.of(getProductEntity()));
        productClient.findByProductName("888888888");
        Mockito.verify(productRepository, Mockito.times(1)).findByName(ArgumentMatchers.any());
    }

    private List<ProductEntity> getListProductEntity() {
        return List.of(getProductEntity());
    }
    private ProductEntity getProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory(getCategory());
        productEntity.setId(1L);
        productEntity.setPrice(50.0);
        productEntity.setInformation("teste");
        productEntity.setName("Teste");

        return productEntity;
    }

    private CategoryEntity getCategory() {

        var category = new CategoryEntity();
        category.setName("Teste");
        category.setId(1L);
        return category;
    }
    private ProductRequestClientDTO getProductRequest() {
        ProductRequestClientDTO productRequest = new ProductRequestClientDTO();
        productRequest.setName("Teste");
        productRequest.setInformation("teste");
        productRequest.setPrice(1.0);
        productRequest.setNameCategory("teste");
        return productRequest;
    }

}