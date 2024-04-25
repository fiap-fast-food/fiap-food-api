package com.fiap.food.api.payment.controller;

import com.fiap.food.api.payment.dto.PaymentResponse;
import com.fiap.food.api.payment.service.PaymentService;
import com.fiap.food.enums.StatusPaymentEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    @DisplayName("Find By ConfirmationCode Should return http code 200 when information is valid")
    void find_by_ConfirmationCode_should_return_http_code_200_when_information_is_valid() throws Exception {
        // Configurar o mock do CategoryService para retornar o objeto simulado quando chamado
        when(paymentService.findStatusPaymentByConfirmationCodeOrder(any())).thenReturn(getPaymentResponse());

        // Executar a solicitação POST com os dados válidos
        mockMvc.perform(get("/api/v1/payment/status/{confirmationCode}", "888888888"))
                .andExpect(status().isOk()) // Verifica se o status da resposta é 200 OK
                .andExpect(jsonPath("$.id").value(1));
    }

    private PaymentResponse getPaymentResponse() {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatusPayment(StatusPaymentEnum.APPROVED);
        paymentResponse.setId(1L);
        paymentResponse.setAmount(BigDecimal.ONE);
        return paymentResponse;
    }

}