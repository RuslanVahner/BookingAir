package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.entity.Airline;

import java.util.List;
import java.util.UUID;

public interface AirlineService {
    Airline getAirlineById(UUID uuid);
    List<Airline> getAllAirlines();
    Airline createAirline(Airline airline);
}