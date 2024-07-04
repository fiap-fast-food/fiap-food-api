package com.fiap.food.client.dto;

import com.fiap.food.api.category.dto.CategoryResponse;
import lombok.Data;

@Data
public class ProductResponseClientDTO {
    private Long id;
    private String name;
    private Double price;
    private CategoryResponse category;
    private String information;
}
