package com.vahner.airticketsapp.dto;

import lombok.*;

@Value
@Builder
@AllArgsConstructor
public class TripsDto {

    String uuid;
    String nameTrips;
    String flightTime;
    String tripsType;

}