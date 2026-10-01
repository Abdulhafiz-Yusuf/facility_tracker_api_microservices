package com.aySmartTech.facility.dtos;

import com.aySmartTech.facility.FacilityStatus;
import com.aySmartTech.facility.FacilityType;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.aySmartTech.facility.entities.Facility}
 */
@Value
public class FacilityResponseDto {
    Long id;
    Long customerId;
    FacilityType facilityType;
    BigDecimal principal;
    BigDecimal profitRate;
    FacilityStatus status;
    LocalDateTime createdAt;
}
