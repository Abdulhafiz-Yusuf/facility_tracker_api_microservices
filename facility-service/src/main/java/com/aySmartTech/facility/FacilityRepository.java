package com.aySmartTech.facility;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aySmartTech.facility.entities.Facility;
import com.aySmartTech.facility.entities.FacilityStatus;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByAccountNumber(String accountNumber);
    List<Facility> findByStatus(FacilityStatus status);


}
