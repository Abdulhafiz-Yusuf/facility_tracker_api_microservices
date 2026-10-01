package com.aySmartTech.facility.dtos;


import com.aySmartTech.facility.FacilityStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

/**
 * DTO for {@link com.aySmartTech.facility.entities.ay_smart_tech.facility_tracker_api.facility.Facility}
 */
@Value
public class FacilityStatusUpdateRequestDto {
    @NotNull
    FacilityStatus status;
}
