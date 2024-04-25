package com.fiap.food.api.order.service;

import com.fiap.food.api.assembler.CustomerMapper;
import com.fiap.food.api.assembler.OrderMapper;
import com.fiap.food.api.assembler.PaymentMapper;
import com.fiap.food.api.assembler.ProductMapper;
import com.fiap.food.api.customer.service.CustomerService;
import com.fiap.food.api.order.dto.OrderRequest;
import com.fiap.food.api.payment.dto.PaymentRequest;
import com.fiap.food.api.product.dto.ProductRequest;
import com.fiap.food.api.product.service.ProductService;
import com.fiap.food.client.dto.PaymentRequestClientDTO;
import com.fiap.food.client.service.PaymentClientService;
import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.core.model.OrderEntity;
import com.fiap.food.core.model.PaymentEntity;
import com.fiap.food.core.repository.OrderRepository;
import com.fiap.food.enums.OrderStatus;
import com.fiap.food.enums.StatusPaymentEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderEntityMapper;


    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private PaymentMapper paymentEntityMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PaymentClientService paymentClientService;

    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> orders = orderRepository.findOrdersOrderedByStatusAndDateTimeOrder();
        return orders;
    }

    @Override
    public OrderEntity findByConfirmationCode(String confirmationCode) throws NotFoundException {
        var orderEntity = orderRepository.findByConfirmationCode(confirmationCode).orElseThrow(() -> new NotFoundException("Order not found"));
        return orderEntity;
    }

    @Override
    public void insert(String cpfCustomer, List<String> productsName) throws NotFoundException {
        var order = new OrderRequest();
        order.setDateTimeOrder(LocalDateTime.now());
        order.setStatus(OrderStatus.RECEIVED);
        order.setConfirmationCode(UUID.randomUUID().toString());

        vincularCliente(cpfCustomer, order);
        processaProdutos(productsName, order);

        var payment = processaPagamento(order);

        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        PaymentEntity paymentEntity = paymentEntityMapper.toEntity(payment);

        paymentEntity.setOrder(orderEntity);
        orderEntity.setPayment(paymentEntity);

        orderRepository.save(orderEntity);
    }

    private void vincularCliente(String cpfCustomer, OrderRequest order) throws NotFoundException {
        // Customer
        if (!Objects.isNull(cpfCustomer) && !cpfCustomer.isBlank()) {
            var customer = customerService.findByCpf(cpfCustomer);
            order.setCustomer(customerMapper.toRequest(customer));
        }
    }

    private void processaProdutos(List<String> productsName, OrderRequest order) {
        // Product
        List<ProductRequest> products = new ArrayList<>();

        productsName.forEach(productName -> {
            try {
                var byProductName = productService.findByProductName(productName);
                products.add(productMapper.toRequest(byProductName));
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        // Associando a lista de produtos à pedido
        order.setProducts(products);
    }

    private PaymentRequest processaPagamento(OrderRequest order) throws NotFoundException {
        var payment = new PaymentRequest();
        var totalSumOrderAmount = order.getProducts().stream()
                        .mapToDouble(ProductRequest::getPrice)
                                .sum();

        PaymentRequestClientDTO paymentRequestClientDTO = new PaymentRequestClientDTO();
        paymentRequestClientDTO.setTimestamp(LocalDateTime.now());
        paymentRequestClientDTO.setAmount(totalSumOrderAmount);
        paymentRequestClientDTO.setId_transaction(order.getConfirmationCode());
        String statusPayment = paymentClientService.processarPagamento(paymentRequestClientDTO);

        payment.setStatusPayment(StatusPaymentEnum.fromString(statusPayment));
        payment.setAmount(new BigDecimal(totalSumOrderAmount));
        payment.setOrder(order);
        return payment;
    }

    @Override
    public void updateConfirmOrder(OrderEntity order) {
        orderRepository.save(order);
    }

    @Override
    public void putStatusOrderByConfirmationCodeAndStatus(String confirmationCode, String status) throws NotFoundException {
        OrderEntity order = orderRepository.findByConfirmationCode(confirmationCode).orElseThrow(() -> new NotFoundException("Order not found"));
        var orderStatus = OrderStatus.fromString(status);
        order.setStatus(orderStatus);
        orderRepository.save(order);
    }
}
