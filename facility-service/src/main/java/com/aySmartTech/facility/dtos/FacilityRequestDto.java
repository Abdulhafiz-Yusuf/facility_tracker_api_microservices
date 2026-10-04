package com.aySmartTech.facility.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.math.BigDecimal;

import com.aySmartTech.facility.entities.FacilityType;

/**
 * DTO for {@link com.aySmartTech.facility}
 */
@Value
public class FacilityRequestDto {
    @NotBlank(message = "accountNumber can not be empty")
    @Pattern(regexp = "\\d{10}", message = "accountNumber must be 10 digits")
    private String accountNumber;

    @NotNull(message = "Facility type is required")
    private FacilityType facilityType;


    @Positive(message = "principalAmount must be greater than zero")
    private BigDecimal principalAmount;

}
