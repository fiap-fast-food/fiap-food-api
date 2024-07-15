package com.fiap.food.client.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentRequestClientDTO {
    // teste deploy
    private Double amount;
    private LocalDateTime timestamp;
    private String status;
    private String id_transaction;

}
