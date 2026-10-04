package com.aySmartTech.Payment_Service.service;

import java.math.BigDecimal;
import java.util.List;

import com.aySmartTech.Payment_Service.dtos.PaymentRequestDto;
import com.aySmartTech.Payment_Service.dtos.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);
    PaymentResponseDto getPayment(Long id);
    List<PaymentResponseDto> getAllPayments();
    PaymentResponseDto updatePayment(Long id, PaymentRequestDto paymentRequestDto);
    
    // other service request
    BigDecimal getTotalAmoundPaidByFacilityId(Long facilityId);


}