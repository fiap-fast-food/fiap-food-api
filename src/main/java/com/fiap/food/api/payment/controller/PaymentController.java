package com.fiap.food.api.payment.controller;

import com.fiap.food.api.payment.dto.PaymentResponse;
import com.fiap.food.api.payment.service.PaymentService;
import com.fiap.food.core.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Payment", description = "the Payment Api")
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Operation(
            summary = "find status payment by confirmationCode Order",
            description = "find status payment order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    @GetMapping("/status/{confirmationCode}")
    public ResponseEntity<PaymentResponse> findStatusByConfirmationCode(@PathVariable String confirmationCode) throws NotFoundException {
        return ResponseEntity.ok().body(paymentService.findStatusPaymentByConfirmationCodeOrder(confirmationCode));
    }
}
