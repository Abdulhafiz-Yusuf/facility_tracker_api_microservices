package com.aySmartTech.Payment_Service;

import com.aySmartTech.Payment_Service.dtos.PaymentRequestDto;
import com.aySmartTech.Payment_Service.dtos.PaymentResponseDto;
import com.aySmartTech.Payment_Service.service.PaymentServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor 
class PaymentController {

    private final PaymentServiceImpl service;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(
            @Valid @RequestBody PaymentRequestDto request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createPayment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getPayment(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getPayments(
            @RequestParam(required = false) Long facilityId){
        // if(facilityId != null) {
        //     return ResponseEntity
        //             .status(HttpStatus.OK)
        //             .body(service.getPayment(facilityId));
        // }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllPayments());
    }

    // other service request
    @GetMapping("/{facilityId}/totalPaid")
    public BigDecimal getTotalAmoundPaidByFacilityId(@PathVariable("facilityId")  Long facilityId) {
        return service.getTotalAmoundPaidByFacilityId(facilityId);
    }
    


}
