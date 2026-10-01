package com.aySmartTech.facility;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceClient {

}
