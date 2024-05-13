package com.fiap.food.api.order.controller;

import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.api.order.dto.OrderRequest;
import com.fiap.food.api.order.dto.OrderResponse;
import com.fiap.food.api.order.service.OrderService;
import com.fiap.food.core.model.CustomerEntity;
import com.fiap.food.core.model.OrderEntity;
import com.fiap.food.enums.OrderStatus;
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

import java.time.LocalDateTime;
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
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @Autowired
    private JacksonTester<OrderRequest> orderRequestJackson;
    @Autowired
    private JacksonTester<OrderResponse> orderResponseJackson;

    @Test
    @DisplayName("Should return http code 400 when information is invalid")
    void should_return_http_code_400_when_information_is_invalid() throws Exception {
        var response = mockMvc.perform(post("/api/v1/orders") .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when information is valid")
    void should_return_http_code_200_when_information_is_valid() throws Exception {
        doNothing().when(orderService).insert("88888888", List.of("teste", "teste"));

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(post("/api/v1/orders") .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestJackson.write(getOrderRequest()).getJson()))
                .andExpect(status().isOk()); // Verificar se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find ALL Should return http code 200 when information is valid")
    void find_ALL_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        when(orderService.findAll()).thenReturn(List.of(getOrderEntity()));

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/orders").with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Find By ConfirmationCode Should return http code 200 when information is valid")
    void find_by_CPF_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do OrderService para retornar o objeto simulado quando chamado
        when(orderService.findByConfirmationCode(any())).thenReturn(getOrderEntity());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/orders/{confirmationCode}", "888888888").with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 200 OK
    }

    @Test
    @DisplayName("Update By Id Should return http code 204 when information is valid")
    void update_by_id_should_return_http_code_200_when_information_is_valid() throws Exception {

        when(orderService.findByConfirmationCode(any())).thenReturn(getOrderEntity());
        doNothing().when(orderService).updateConfirmOrder(any());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(put("/api/v1/orders/confirm/{confirmationCode}", 1L).with(SecurityMockMvcRequestPostProcessors.jwt()).contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestJackson.write(getOrderRequest()).getJson()))
                .andExpect(status().isNoContent()); // Verifica se o status da resposta é 204 OK
    }

    @Test
    @DisplayName("Update Status Order Should return http code 204 when information is valid")
    void update_status_order_should_return_http_code_200_when_information_is_valid() throws Exception {


        doNothing().when(orderService).putStatusOrderByConfirmationCodeAndStatus(any(), any());


        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(put("/api/v1/orders/status/{confirmationCode}/{statusOrder}", 1L, 1L).with(SecurityMockMvcRequestPostProcessors.jwt()).contentType(MediaType.APPLICATION_JSON)
                        .content(orderRequestJackson.write(getOrderRequest()).getJson()))
                .andExpect(status().isOk()); // Verifica se o status da resposta é 204 OK
    }




    private OrderEntity getOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setCustomer(getCustomerEntity());
        orderEntity.setStatus(OrderStatus.IN_PREPARATION);
        orderEntity.setConfirmationCode("1234564654");

        return orderEntity;
    }
    private OrderRequest getOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustomer(getCustomerRequest());
        orderRequest.setDateTimeOrder(LocalDateTime.now());
        orderRequest.setStatus(OrderStatus.IN_PREPARATION);
        orderRequest.setCpfCustomer("88888888888");
        orderRequest.setProductsName(List.of("teste", "teste"));

        return orderRequest;
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