package com.fiap.food.api.product.controller;

import com.fiap.food.api.assembler.CategoryMapper;
import com.fiap.food.api.assembler.ProductMapper;
import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.api.product.dto.ProductResponse;
import com.fiap.food.api.product.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private JacksonTester<ProductRequest> productRequestJackson;
    @Autowired
    private JacksonTester<ProductResponse> productResponseJackson;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    void should_return_http_code_400_when_information_is_invalid() throws Exception {
        var response = mockMvc.perform(post("/api/v1/products"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    void should_return_http_code_200_when_information_is_valid() throws Exception {
        when(productService.insert(any())).thenReturn(productMapper.toEntity(getProductRequest()));

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestJackson.write(getProductRequest()).getJson()))
                .andExpect(status().isOk()); // Verificar se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find By ID Should return http code 200 when information is valid")
    void find_by_ID_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do ProductService para retornar o objeto simulado quando chamado
        when(productService.findById(any())).thenReturn(productMapper.toEntity(getProductRequest()));

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/products/{id}", 1L))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find By Category Name Should return http code 200 when information is valid")
    void find_by_Category_Name_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do ProductService para retornar o objeto simulado quando chamado
        when(productService.findByCategoryName(any())).thenReturn(List.of(productMapper.toEntity(getProductRequest())));

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/products/category/{categoryName}", "Teste"))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find By Product Name Should return http code 200 when information is valid")
    void find_by_Product_Name_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do ProductService para retornar o objeto simulado quando chamado
        when(productService.findByProductName(any())).thenReturn(productMapper.toEntity(getProductRequest()));

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/products/product/{productName}", "Teste"))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Update By Id Should return http code 204 when information is valid")
    void update_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {


        // Configurar o mock do ProductService para retornar o objeto simulado quando chamado
        doNothing().when(productService).update(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(put("/api/v1/products/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestJackson.write(getProductRequest()).getJson()))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }


    @Test
    @DisplayName("DELETE By Id Should return http code 204 when information is valid")
    void delete_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {

        // Configurar o mock do ProductService para retornar o objeto simulado quando chamado
        doNothing().when(productService).delete(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(delete("/api/v1/products/{id}", 1L))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }


    private ProductRequest getProductRequest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Teste");
        productRequest.setInformation("teste");
        productRequest.setPrice(1.0);
        productRequest.setNameCategory("teste");
        return productRequest;
    }





}