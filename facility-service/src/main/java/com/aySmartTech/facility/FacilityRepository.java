package com.aySmartTech.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aySmartTech.facility.entities.Facility;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByCustomerId(Long customerId);
    List<Facility> findByStatus(FacilityStatus status);


}
