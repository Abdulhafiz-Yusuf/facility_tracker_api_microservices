package com.aySmartTech.facility;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import com.aySmartTech.facility.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.aySmartTech.facility.dtos.FacilityResponseDto;
import com.aySmartTech.facility.entities.Facility;

@Component
public class FacilityUtil {

    public static FacilityResponseDto toResponse(Facility facility, BigDecimal totalAmountPaid, BigDecimal outstandingBal){
        return new FacilityResponseDto(
            facility.getId(),
            facility.getFacilityType(),
            facility.getPrincipalAmount(),
            facility.getProfitRate(),
            facility.getStatus(),
            facility.getAccountNumber(),
            facility.getFacilityNumber(),
            totalAmountPaid,
            outstandingBal,
            facility.getCreatedAt()
        );
    }

    public static String generateFacilityNumber(){
        return String.valueOf(1000000000L + new Random().nextLong(900000000));
    }


    // ---- Private helper: builds the response with the CORRECT status ----
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


    public static BigDecimal getTotalAmountPai(Long facilityId, PaymentServiceClient paymentServiceClient){
        
        return paymentServiceClient.getTotalAmoundPaidByFacilityId(facilityId);
        
    }

     public static BigDecimal caculateoutstandingBalance(BigDecimal principalAmount, BigDecimal totalAmountPaid){
        
        return principalAmount.subtract(totalAmountPaid);
        
    }
}
