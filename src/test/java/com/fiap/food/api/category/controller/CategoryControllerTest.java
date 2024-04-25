package com.fiap.food.api.category.controller;

import com.fiap.food.api.assembler.CategoryMapper;
import com.fiap.food.api.category.dto.CategoryRequest;
import com.fiap.food.api.category.dto.CategoryResponse;
import com.fiap.food.api.category.service.CategoryService;
import com.fiap.food.core.model.CategoryEntity;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CategoryControllerTest {
    @Autowired
    private JacksonTester<CategoryRequest> categoryRequest;
    @Autowired
    private JacksonTester<CategoryResponse> categoryResponse;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    void should_return_http_code_400_when_information_is_invalid() throws Exception {
        var response = mockMvc.perform(post("/api/v1/category"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    void should_return_http_code_200_when_information_is_valid() throws Exception {
        // Criar um objeto CategoryEntity simulado
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Teste");

        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        when(categoryService.insert(any())).thenReturn(categoryEntity);

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryRequest.write(getCategoryRequest()).getJson()))
                .andExpect(status().isOk()); // Verificar se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find By Id Should return http code 200 when information is valid")
    void find_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Criar um objeto CategoryEntity simulado
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Teste");

        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        when(categoryService.find(1L)).thenReturn(categoryEntity);

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/category/{id}", 1L))
                .andExpect(status().isOk()) // Verifica se o status da resposta é 200 OK
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Update By Id Should return http code 204 when information is valid")
    void update_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Criar um objeto CategoryEntity simulado
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Teste");

        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        doNothing().when(categoryService).update(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(put("/api/v1/category/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(categoryRequest.write(getCategoryRequest()).getJson()))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }

    @Test
    @DisplayName("DELETE By Id Should return http code 204 when information is valid")
    void delete_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Criar um objeto CategoryEntity simulado
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Teste");

        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        doNothing().when(categoryService).delete(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(delete("/api/v1/category/{id}", 1L))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }



    private CategoryRequest getCategoryRequest() {
        var category = new CategoryRequest();
        category.setName("Teste");
        return category;
    }

    private CategoryEntity getCategory() {

        var category = new CategoryEntity();
        category.setName("Teste");
        category.setId(1L);
        return category;
    }


}