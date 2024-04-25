package com.fiap.food.api.customer.controller;

import com.fiap.food.api.assembler.CustomerMapper;
import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.api.customer.service.CustomerService;
import com.fiap.food.core.model.CategoryEntity;
import com.fiap.food.core.model.CustomerEntity;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private JacksonTester<CustomerRequest> customerRequestJackson;
    @Autowired
    private JacksonTester<CustomerResponse> customerResponseJackson;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    void should_return_http_code_400_when_information_is_invalid() throws Exception {
        var response = mockMvc.perform(post("/api/v1/customers"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    void should_return_http_code_200_when_information_is_valid() throws Exception {
        doNothing().when(customerService).insert(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestJackson.write(getCustomerRequest()).getJson()))
                .andExpect(status().isOk()); // Verificar se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find By CPF Should return http code 200 when information is valid")
    void find_by_CPF_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        when(customerService.findByCpf(any())).thenReturn(getCustomerEntity());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/customers/identify/{cpf}", "888888888"))
                .andExpect(status().isOk()) // Verifica se o status da resposta é 200 OK
                .andExpect(jsonPath("$.id").value(1));
    }


    @Test
    @DisplayName("Find By ID Should return http code 200 when information is valid")
    void find_by_ID_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        when(customerService.findById(any())).thenReturn(getCustomerEntity());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/customers/{id}", 1L))
                .andExpect(status().isOk()) // Verifica se o status da resposta é 200 OK
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Update By Id Should return http code 204 when information is valid")
    void update_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {


        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        doNothing().when(customerService).update(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(put("/api/v1/customers/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(customerRequestJackson.write(getCustomerRequest()).getJson()))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }

    @Test
    @DisplayName("DELETE By Id Should return http code 204 when information is valid")
    void delete_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {

        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        doNothing().when(customerService).delete(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(delete("/api/v1/customers/{id}", 1L))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }


    private CustomerEntity getCustomerEntity() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("Teste");
        customerEntity.setCpf("00000000000");
        customerEntity.setEmail("teste@mail");
        return customerEntity;
    }

    private CustomerResponse getCustomerResponse() {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCpf("00000000000");
        customerResponse.setEmail("test@mail.com");
        customerResponse.setId(1L);
        customerResponse.setName("teste");

        return customerResponse;
    }

    private CustomerRequest getCustomerRequest() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCpf("00000000000");
        customerRequest.setEmail("teste@mail.com");
        customerRequest.setPassword("123456789");
        customerRequest.setName("teste");

        return customerRequest;
    }

}