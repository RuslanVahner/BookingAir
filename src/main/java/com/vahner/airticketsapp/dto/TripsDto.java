package com.vahner.airticketsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripsDto {

    String uuid;
    String nameTrips;
    String flightTime;
    String tripsType;

}