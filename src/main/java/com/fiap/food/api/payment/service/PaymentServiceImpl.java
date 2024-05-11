package com.fiap.food.api.payment.service;

import com.fiap.food.api.assembler.PaymentMapper;
import com.fiap.food.api.payment.dto.PaymentResponse;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.PaymentEntity;
import com.fiap.food.core.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentEntityMapper;
    @Override
    public PaymentResponse findStatusPaymentByConfirmationCodeOrder(String confirmationCode) throws NotFoundException {
        PaymentEntity byOrderConfirmationCode = paymentRepository.findByOrderConfirmationCode(confirmationCode).orElseThrow(() -> new NotFoundException("Order not found"));
        return paymentEntityMapper.toOutput(byOrderConfirmationCode);
    }
}
