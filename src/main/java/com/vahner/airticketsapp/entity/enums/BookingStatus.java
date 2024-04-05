package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {
    PENDING("PENDING"),
    BOOKED("BOOKED"),
    CANCELLED("CANCELLED");

    private final String booking;


    BookingStatus(String booking) {
        this.booking = booking;
    }
}