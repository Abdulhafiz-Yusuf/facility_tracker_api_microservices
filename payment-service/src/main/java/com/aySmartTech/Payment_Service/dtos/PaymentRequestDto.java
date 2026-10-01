package com.aySmartTech.Payment_Service.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.aySmartTech.Payment_Service.Payment}
 */
public record PaymentRequestDto(
        @NotNull(message = "Facility ID is required")
        @Positive(message = "Facility ID must be positive and can not be zero")
        Long facilityId,


        @DecimalMin(value = "0.01", message = "Amount paid must be greater than zero")
        @NotNull(message = "Payment amount is required")
        BigDecimal amountPaid
)  {}
