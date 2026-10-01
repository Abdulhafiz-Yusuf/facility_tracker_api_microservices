package com.aySmartTech.facility.dtos;

import com.aySmartTech.facility.FacilityType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;

/**
 * DTO for {@link com.aySmartTech.facility}
 */
@Value
public class FacilityRequestDto {
    @NotNull(message = "Customer ID is required")
    Long customerId;

    @NotNull(message = "Facility type is required")
    FacilityType facilityType;

    @NotNull(message = "Principal is required")
    @DecimalMin(value = "0.01", message = "Principal must be greater than zero")
    BigDecimal principal;

    @NotNull(message = "Profit rate is required")
    @DecimalMin(value = "0.0", message = "Profit rate cannot be negative")
    BigDecimal profitRate;
}
