package com.fiap.food.api.product.dto;

import com.fiap.food.api.category.dto.CategoryResponse;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private CategoryResponse category;
    private String information;
}
