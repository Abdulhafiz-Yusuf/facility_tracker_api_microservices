package com.aySmartTech.Payment_Service.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aySmartTech.Payment_Service.PaymentUtils;
import com.aySmartTech.Payment_Service.dtos.ErrorResponseDto;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ---- 404 Not Found ----
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(
            ResourceNotFoundException e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.NOT_FOUND, e.getMessage(), req);
    }


    // ---- 409 Conflict: duplicate resource (e.g. email already exists) ----
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicate(DuplicateResourceException e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.CONFLICT, e.getMessage(), req);
    }

    // ---- 409 Conflict: illegal Facility state transition ----
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalState(IllegalStateException e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.CONFLICT, e.getMessage(), req);
    }

    // ---- 409 Conflict: business rule violation (e.g. overpayment, inactive facility) ----
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessRule(BusinessRuleException e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.CONFLICT, e.getMessage(), req);
    }

    // ---- 400 Bad Request: Jakarta Validation failures (@Valid on DTOs) ----
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(
        MethodArgumentNotValidException e, HttpServletRequest req) {

        String message = e.getBindingResult().getFieldErrors().stream()
            .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
            .collect(Collectors.joining("; "));

        if (message.isBlank()) {
            message = "Validation failed";
        }

        return PaymentUtils.build(HttpStatus.BAD_REQUEST, message, req);
    }
    
    // ---- 401 Unauthorized: wrong email/password on login ----
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentials(BadCredentialsException e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.UNAUTHORIZED, e.getMessage(), req);
    }

    // ---- 403 Forbidden: authenticated but not permitted (e.g. wrong role) ----
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDenied(AccessDeniedException e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.FORBIDDEN, e.getMessage(), req);
    }

    // ---- 500 fallback: anything unhandled above ----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneric(Exception e, HttpServletRequest req) {
        return PaymentUtils.build(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), req);
    }



}
