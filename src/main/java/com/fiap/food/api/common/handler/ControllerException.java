package com.fiap.food.api.common.handler;

import com.fiap.food.core.exception.NotFoundException;
import com.fiap.food.api.common.dto.ValidationFieldErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerException {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationFieldErrorResponse> handle(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrorsList = exception.getBindingResult().getFieldErrors();
        List<ValidationFieldErrorResponse> validationFieldErrorResponseList = new ArrayList<>();

        fieldErrorsList.forEach(error -> {
            validationFieldErrorResponseList.add(new ValidationFieldErrorResponse(
                    error.getField(),
                    error.getDefaultMessage(),
                    LocalDateTime.now()));
        });

        return validationFieldErrorResponseList;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String notFound(NotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = "Erro ao excluir devido a uma restrição de chave estrangeira";
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

}
