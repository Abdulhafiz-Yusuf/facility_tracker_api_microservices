package com.aySmartTech.Payment_Service.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aySmartTech.Payment_Service.PaymentRepository;
import com.aySmartTech.Payment_Service.dtos.PaymentRequestDto;
import com.aySmartTech.Payment_Service.dtos.PaymentResponseDto;
import com.aySmartTech.Payment_Service.entities.Payment;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repo;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaymentResponseDto getPayment(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BigDecimal getTotalAmoundPaidByFacilityId(Long facilityId) {
        List<Payment> payments = repo.findByFacilityId(facilityId);

        if (payments != null && !payments.isEmpty())
            return repo.sumAmountPaid(facilityId);

        return BigDecimal.ZERO;
    }

    @Override
    public PaymentResponseDto updatePayment(Long id, PaymentRequestDto paymentRequestDto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
