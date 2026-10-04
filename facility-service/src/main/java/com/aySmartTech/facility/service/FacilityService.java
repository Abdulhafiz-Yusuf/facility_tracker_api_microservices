package com.aySmartTech.facility.service;

import com.aySmartTech.facility.dtos.FacilityRequestDto;
import com.aySmartTech.facility.dtos.FacilityResponseDto;

import java.util.List;

public interface FacilityService {
     //create a facility
    FacilityResponseDto createFacility(FacilityRequestDto facilityRequestDto);

    // get loan list
    List<FacilityResponseDto> getAllLoans();

    // get a specific loan
    FacilityResponseDto getLoanById(Long Id);

    // update a specific loan
    FacilityResponseDto updateLoan(Long id, FacilityRequestDto facilityRequestDto);

    // delete a loan
    void deleteLoan(Long Id);
}
