package com.vahner.airticketsapp.dto;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
@Data
public class TripsDto {

    String nameTrips;
    String flightNumber;
    String flightTime;
    String arrival;

    List<AirlineDto> airlineDtoList;
}