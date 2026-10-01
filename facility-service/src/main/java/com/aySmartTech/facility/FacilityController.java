package com.aySmartTech.facility;

import com.aySmartTech.facility.dtos.FacilityRequestDto;
import com.aySmartTech.facility.dtos.FacilityResponseDto;
import com.aySmartTech.facility.dtos.FacilityStatusUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/facilities")
class FacilityController {

    private final FacilityService service;

//    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<FacilityResponseDto> createFacility(
            @Valid @RequestBody FacilityRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createFacility(requestDto));
    }


//    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @PatchMapping("{id}/status")
    public ResponseEntity<FacilityResponseDto> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody FacilityStatusUpdateRequestDto requestDto
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.updateStatus(id,requestDto.getStatus()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<FacilityResponseDto> getFacilityById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getFacilityById(id));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    @GetMapping
    public ResponseEntity<List<FacilityResponseDto>> getFacilities(
            @Valid @RequestParam(required = false) Long customerId
    ){
    if(customerId !=  null){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getFacilitiesByCustomer(customerId));
    }
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAllFacilities());
    }


}
