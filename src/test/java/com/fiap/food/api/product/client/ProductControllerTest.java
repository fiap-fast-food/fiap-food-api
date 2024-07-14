package com.fiap.food.api.product.client;

import com.fiap.food.api.assembler.CategoryMapper;
import com.fiap.food.api.assembler.ProductMapper;
import com.fiap.food.client.dto.ProductRequestClientDTO;
import com.fiap.food.client.dto.ProductResponseClientDTO;
import com.fiap.food.client.service.ProductClient;
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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
    private ProductClient productClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private JacksonTester<ProductRequestClientDTO> productRequestJackson;
    @Autowired
    private JacksonTester<ProductResponseClientDTO> productResponseJackson;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    void should_return_http_code_400_when_information_is_invalid() throws Exception {
        var response = mockMvc.perform(post("/api/v1/products").with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Find By Product Name Should return http code 200 when information is valid")
    void find_by_Product_Name_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do ProductService para retornar o objeto simulado quando chamado
        when(productClient.findByProductName(any())).thenReturn(getProductRequest());

        // Executar a solicitação GET com os dados válidos
        mockMvc.perform(get("/api/v1/products/product/{productName}", "Teste")
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK
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