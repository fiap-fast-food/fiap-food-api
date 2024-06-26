package com.fiap.food.client.service;

import com.fiap.food.client.dto.PaymentRequestClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentClientService {
    @Value("${payment.webhook.url}")
    private String urlPaymentWebHook;

    private final RestTemplate restTemplate;

    public String processarPagamento(PaymentRequestClientDTO requestClientDTO) {
        HttpEntity<PaymentRequestClientDTO> request = new HttpEntity<>(requestClientDTO);

        ResponseEntity<String> response = restTemplate.postForEntity(urlPaymentWebHook + "/payment", request, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Retorna a String com o status do pagamento
        } else {
            throw new RuntimeException("Falha ao processar o pagamento. Status: " + response.getStatusCode());
        }
    }
}
