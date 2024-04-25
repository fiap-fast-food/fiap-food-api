package com.fiap.food.api.payment.service;

import com.fiap.food.api.assembler.PaymentMapper;
import com.fiap.food.api.payment.dto.PaymentResponse;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.PaymentEntity;
import com.fiap.food.core.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentEntityMapper;
    @Override
    public PaymentResponse findStatusPaymentByConfirmationCodeOrder(String confirmationCode) throws NotFoundException {
        PaymentEntity byOrderConfirmationCode = paymentRepository.findByOrderConfirmationCode(confirmationCode).orElseThrow(() -> new NotFoundException("Order not found"));
        return paymentEntityMapper.toOutput(byOrderConfirmationCode);
    }
}
