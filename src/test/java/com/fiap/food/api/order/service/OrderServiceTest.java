package com.fiap.food.api.order.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.OrderEntity;
import com.fiap.food.core.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void testFindByConfirmationCode() throws NotFoundException {
        OrderEntity order = new OrderEntity();
        order.setConfirmationCode("CONF123");
        when(orderRepository.findByConfirmationCode("CONF123")).thenReturn(Optional.of(order));

        OrderEntity foundOrder = orderService.findByConfirmationCode("CONF123");

        assertNotNull(foundOrder);
        assertEquals("CONF123", foundOrder.getConfirmationCode());
    }
}
