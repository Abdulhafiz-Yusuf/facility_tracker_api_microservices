package com.aySmartTech.Payment_Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aySmartTech.Payment_Service.dtos.ErrorResponseDto;
import com.aySmartTech.Payment_Service.dtos.PaymentResponseDto;
import com.aySmartTech.Payment_Service.entities.Payment;


public class PaymentUtils {

    public static PaymentResponseDto toResponse(
        Payment payment, BigDecimal totalAmountPaid, BigDecimal outstandingBal){
        return new PaymentResponseDto(
            payment.getId(),
            payment.getFacilityId(),
            payment.getAmountPaid(),
            totalAmountPaid,
            outstandingBal,
            payment.getPaymentDate()
        );
    }

    public static String generatePaymentNumber(){
        return String.valueOf(1000000000L + new Random().nextLong(900000000));
    }


    public static String generateAccountNumber(){
        return String.valueOf(1000000000L + new Random().nextLong(900000000));
    }


    // ---- helper: builds the response with the CORRECT status ----
    public static ResponseEntity<ErrorResponseDto> build(
            HttpStatus status, String message, HttpServletRequest req) {
        ErrorResponseDto body = new ErrorResponseDto(
                req.getRequestURI(),
                status,
                message,
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(body);
    }

    
    
}
