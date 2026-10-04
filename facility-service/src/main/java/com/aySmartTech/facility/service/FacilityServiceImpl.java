package com.aySmartTech.facility.service;

import com.aySmartTech.facility.exceptions.DuplicateResourceException;
import com.aySmartTech.facility.exceptions.ResourceNotFoundException;
import com.aySmartTech.facility.FacilityRepository;
import com.aySmartTech.facility.FacilityUtil;
import com.aySmartTech.facility.PaymentServiceClient;
import com.aySmartTech.facility.dtos.FacilityRequestDto;
import com.aySmartTech.facility.dtos.FacilityResponseDto;
import com.aySmartTech.facility.entities.Facility;
import com.aySmartTech.facility.entities.FacilityStatus;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.logging.LoggingRebinder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class FacilityServiceImpl {
    private final FacilityRepository facilityRepo;
     private final PaymentServiceClient paymentServiceClient;
    

    @Transactional
    public FacilityResponseDto createFacility(FacilityRequestDto request){
//        if(!customerRepo.existsById(request.getCustomerId())){
//            throw new ResourceNotFoundException(
//                    "Customer not found with id " +  request.getCustomerId());
//        }

        if(facilityRepo.findByAccountNumber(request.getAccountNumber()).stream()
                .anyMatch(f->
                        f.getStatus()  == FacilityStatus.PENDING
                                && f.getFacilityType() == request.getFacilityType()
                )
        ){
            throw new DuplicateResourceException("Customer with id "
                    + request.getAccountNumber()
                    + " have pending facility" );
        }

        Facility facility = new Facility();
        facility.setFacilityNumber(FacilityUtil.generateFacilityNumber());
        facility.setFacilityType(request.getFacilityType());
        facility.setPrincipalAmount(request.getPrincipalAmount());
        facility.setProfitRate(request.getFacilityType().getProfitRate());
        facility.setAccountNumber(request.getAccountNumber());

        log.info("Creating facility: number={}, account={}, principal={}, status={}",
                facility.getFacilityNumber(),
                facility.getAccountNumber(),
                facility.getPrincipalAmount(),
                facility.getStatus());

        // status defaults to PENDING via @PrePersist
        Facility savedfFacility = facilityRepo.save(facility);

        BigDecimal totalAmountpaid = FacilityUtil.getTotalAmountPai(savedfFacility.getId(), paymentServiceClient);
        BigDecimal outstandingBalance = FacilityUtil.caculateoutstandingBalance(savedfFacility.getPrincipalAmount(), totalAmountpaid);
        return FacilityUtil.toResponse(savedfFacility, totalAmountpaid, outstandingBalance);
    }

    @Transactional(readOnly = true)
    public FacilityResponseDto getFacilityById(Long id) {
        Facility facility = facilityRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id: " + id));

        // calculating totalAmountPaid and outstandingBal
        BigDecimal totalAmountPaid = paymentServiceClient.getTotalAmoundPaidByFacilityId(facility.getId());
        BigDecimal outstandingBal = facility.getPrincipalAmount().subtract(totalAmountPaid);

        return FacilityUtil.toResponse(facility, totalAmountPaid, outstandingBal);
    }

    @Transactional(readOnly = true)
    public List<FacilityResponseDto> getAllFacilities() {
        return facilityRepo.findAll().stream()
                .map(f -> {
                        // calculating totalAmountPaid and outstandingBal
                        BigDecimal totalAmountPaid = paymentServiceClient.getTotalAmoundPaidByFacilityId(f.getId());
                        BigDecimal outstandingBal = f.getPrincipalAmount().subtract(totalAmountPaid);
                        return FacilityUtil.toResponse(f, totalAmountPaid, outstandingBal);
                })
                .toList();        
    }

    @Transactional(readOnly = true)
    public List<FacilityResponseDto> getFacilitiesByAccount(String accountNumber) {
        return facilityRepo.findByAccountNumber(accountNumber).stream()
                .map(f -> {
                        // calculating totalAmountPaid and outstandingBal
                        BigDecimal totalAmountPaid = paymentServiceClient.getTotalAmoundPaidByFacilityId(f.getId());
                        BigDecimal outstandingBal = f.getPrincipalAmount().subtract(totalAmountPaid);
                        return FacilityUtil.toResponse(f, totalAmountPaid, outstandingBal);
                })
                .toList();
    }

    @Transactional
    public FacilityResponseDto updateStatus(Long facilityId, FacilityStatus newStatus) {
        Facility facility = facilityRepo.findById(facilityId).orElseThrow(()->
                new ResourceNotFoundException("Facility not found with id " + facilityId));


        facility.transitionTo(newStatus);
        // IllegalStateException thrown if transition is illegal
        // Handled by GlobalExceptionHandler

         // calculating totalAmountPaid and outstandingBal
        BigDecimal totalAmountPaid = paymentServiceClient.getTotalAmoundPaidByFacilityId(facility.getId());
        BigDecimal outstandingBal = facility.getPrincipalAmount().subtract(totalAmountPaid);

        Facility updated = facilityRepo.save(facility);
        return FacilityUtil.toResponse(updated, totalAmountPaid, outstandingBal);
    }

}
