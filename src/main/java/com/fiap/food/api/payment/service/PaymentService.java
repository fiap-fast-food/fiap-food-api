package com.fiap.food.api.payment.service;

import com.fiap.food.api.payment.dto.PaymentResponse;
import com.fiap.food.core.exception.NotFoundException;

public interface PaymentService {
    PaymentResponse findStatusPaymentByConfirmationCodeOrder(String confirmationCode) throws NotFoundException;
}
