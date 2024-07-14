package com.fiap.food.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequestClientDTO {
    @NotBlank
    private String name;
    @Positive(message = "O preco deve ser maior que zero")
    private Double price;
    @NotBlank
    private String nameCategory;
    @NotBlank
    private String information;
}
