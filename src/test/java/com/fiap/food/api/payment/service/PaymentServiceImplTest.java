package com.fiap.food.api.payment.service;

import com.fiap.food.api.AplicationConfigTest;
import com.fiap.food.api.assembler.PaymentMapper;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.OrderEntity;
import com.fiap.food.core.model.PaymentEntity;
import com.fiap.food.core.repository.PaymentRepository;
import com.fiap.food.enums.OrderStatus;
import com.fiap.food.enums.StatusPaymentEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("PaymentServiceImplTest")
class PaymentServiceImplTest extends AplicationConfigTest {

    @MockBean
    private PaymentRepository paymentRepository;
    @MockBean
    private PaymentMapper paymentEntityMapper;
    @Autowired
    private PaymentService paymentService;

    @Test
    @DisplayName("mustFindStatusPaymentByConfirmationCodeOrder")
    public void mustFindStatusPaymentByConfirmationCodeOrder() throws NotFoundException {
        Mockito.when(paymentRepository.findByOrderConfirmationCode(any())).thenReturn(Optional.of(getPaymentEntity()));
        paymentService.findStatusPaymentByConfirmationCodeOrder("");
        Mockito.verify(paymentRepository, Mockito.times(1)).findByOrderConfirmationCode(ArgumentMatchers.any());
    }

    private PaymentEntity getPaymentEntity() {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(new BigDecimal(30));
        paymentEntity.setStatusPayment(StatusPaymentEnum.APPROVED);
        paymentEntity.setOrder(getOrderEntity());
        paymentEntity.setId(1L);

        return paymentEntity;
    }

    private OrderEntity getOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        orderEntity.setStatus(OrderStatus.IN_PREPARATION);
        orderEntity.setConfirmationCode("1234564654");

        return orderEntity;
    }


}