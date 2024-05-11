package com.fiap.food.api.customer.controller;

import com.fiap.food.api.assembler.CustomerMapper;
import com.fiap.food.api.customer.dto.CustomerRequest;
import com.fiap.food.api.customer.dto.CustomerResponse;
import com.fiap.food.api.customer.service.CustomerService;
import com.fiap.food.core.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer", description = "the Customer Api")
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    @Operation(
            summary = "Insert new Customer",
            description = "save a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "409", description = "conflict operation"),
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerMapper.toEntity(customerRequest);
        customerService.insert(customer);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Search Customer by CPF",
            description = "Search a categoy by cpf")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/identify/{cpf}")
    public ResponseEntity<CustomerResponse> findByCpf(@PathVariable final String cpf) throws NotFoundException {
        var customer = customerService.findByCpf(cpf);
        var customerResponse = customerMapper.toOutput(customer);
        return ResponseEntity.ok().body(customerResponse);
    }
    @Operation(
            summary = "Search Customer",
            description = "Search a categoy by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final Long id) throws NotFoundException {
        var customer = customerService.findById(id);
        var customerResponse = customerMapper.toOutput(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @Operation(
            summary = "Update Customer",
            description = "Update a customer through a customer request and its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "409", description = "conflict operation")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,@RequestBody @Valid CustomerRequest customerRequest) throws NotFoundException {
        var customer = customerMapper.toEntity(customerRequest);
        customer.setId(id);
        customerService.update(customer);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Delete Customer",
            description = "Deletes a customer using id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) throws NotFoundException {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
