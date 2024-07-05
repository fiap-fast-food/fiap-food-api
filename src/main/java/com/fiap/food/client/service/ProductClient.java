package com.fiap.food.client.service;

import com.fiap.food.client.dto.ProductRequestClientDTO;
import com.fiap.food.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductClient {
    @Autowired
    private RestTemplate restTemplate;

    public ProductRequestClientDTO findByProductName(String name) throws NotFoundException {
        String url = "https://localhost:8082/api/v1/products" + name;
        ResponseEntity<ProductRequestClientDTO> response = restTemplate.getForEntity(url, ProductRequestClientDTO.class);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new NotFoundException("Product not found");
        }
        return response.getBody();
    }
}

