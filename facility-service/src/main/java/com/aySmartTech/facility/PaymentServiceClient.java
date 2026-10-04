package com.aySmartTech.facility;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentServiceClient {

    @GetMapping ("/api/payments/{facilityId}/totalPaid")
    public BigDecimal getTotalAmoundPaidByFacilityId(@PathVariable("facilityId")  Long facilityId);

}
