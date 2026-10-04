package com.aySmartTech.facility.dtos;

import com.aySmartTech.facility.entities.FacilityStatus;
import com.aySmartTech.facility.entities.FacilityType;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.aySmartTech.facility.entities.Facility}
 */
@Value
public class FacilityResponseDto {
    private Long facilityId;
    private FacilityType facilityType;
    private BigDecimal principalAmount;
    private Double profitRate;
    private FacilityStatus status;
    private String accountNumber;
    private String facilityNumber;
    private BigDecimal totalAmountPaid;
    private BigDecimal outstandingAmount;
    private LocalDateTime createdAt;
}
