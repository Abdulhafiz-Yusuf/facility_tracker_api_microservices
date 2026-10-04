package com.aySmartTech.facility.entities;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FacilityType {
    MURABAHA(0.28),
    IJARAH(0.20),
    AUTO_FINANCE(0.18);

    private final double profitRate;

    FacilityType(double profitRate) {
        this.profitRate = profitRate;
    }

    public Double getProfitRate() {
        return profitRate;
    }


    @JsonCreator
    public static FacilityType fromString(String value) {
        if (value == null) return null;
        return Arrays.stream(values())
                .filter(t -> t.name().equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown facilityType: '" + value +
                        "'. Allowed: " + Arrays.toString(values())));
    }


}
