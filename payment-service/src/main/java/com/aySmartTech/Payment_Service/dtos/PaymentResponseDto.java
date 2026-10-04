package com.aySmartTech.Payment_Service.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.aySmartTech.Payment_Service.Payment}
 */
public record PaymentResponseDto(
        Long paymentId,
        Long facilityId,
        BigDecimal amountPaid,
        BigDecimal totalAmountPaid,
        BigDecimal outstandingBalance,
        LocalDate paymentDate
)  {}
