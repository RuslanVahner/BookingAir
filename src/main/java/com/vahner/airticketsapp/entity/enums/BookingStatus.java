package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELLED("CANCELLED");

    private final String booking;


    BookingStatus(String booking) {
        this.booking = booking;
    }
}