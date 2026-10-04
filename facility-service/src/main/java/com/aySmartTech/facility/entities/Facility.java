package com.aySmartTech.facility.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facility")
public class Facility extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;


    @Column(name = "facility_number")
    private String facilityNumber;


    @Enumerated(EnumType.STRING)
    @Column(name = "facility_type", length = 20)
    private FacilityType facilityType;


    @Column(name = "principal_amount")
    private BigDecimal principalAmount;


    @Column(name = "profit_rate")
    private Double profitRate;


    // @Column(name = "total_amountPaid")
    // private BigDecimal totalAmountPaid;

    // @Column(name = "outstanding_amount")
    // private BigDecimal outstandingAmount;


    @Enumerated(EnumType.STRING)
    @Column()
    private FacilityStatus status = FacilityStatus.PENDING;

    @Column(name = "start_date")
    private LocalDate startDate;


    // Legal transitions map: current status -> set of statuses it may move to
    private static final Map<FacilityStatus, Set<FacilityStatus>> ALLOWED_TRANSITIONS = Map.of(
            FacilityStatus.PENDING, Set.of(FacilityStatus.APPROVED, FacilityStatus.REJECTED),
            FacilityStatus.APPROVED, Set.of(FacilityStatus.ACTIVE),
            FacilityStatus.ACTIVE, Set.of(FacilityStatus.CLOSED),
            FacilityStatus.CLOSED, Set.of(),
            FacilityStatus.REJECTED, Set.of()
    );

    public void transitionTo(FacilityStatus newStatus){
        Set<FacilityStatus> allowed = ALLOWED_TRANSITIONS.get(this.status);
        if(allowed == null || !allowed.contains(newStatus)) {
            throw new IllegalStateException(
                    "Cannot transition facility from " + this.status + " to " + newStatus
            );
        }
        this.status = newStatus;
        if(newStatus == FacilityStatus.ACTIVE && this.startDate == null){
            this.startDate = LocalDate.now();
        }

    }



}
