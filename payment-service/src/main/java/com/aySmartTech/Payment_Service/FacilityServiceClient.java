package com.aySmartTech.Payment_Service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "FACILITY-SERVICE")
public interface FacilityServiceClient {
}
