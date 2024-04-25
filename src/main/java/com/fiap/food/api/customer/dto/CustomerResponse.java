package com.fiap.food.api.customer.dto;

import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String cpf;
    private String email;
}
