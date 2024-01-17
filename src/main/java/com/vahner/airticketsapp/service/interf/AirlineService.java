package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.entity.Airline;

import java.util.UUID;

public interface AirlineService {
    Airline getAirlineById(UUID id);
    Airline create(Airline airline);
}