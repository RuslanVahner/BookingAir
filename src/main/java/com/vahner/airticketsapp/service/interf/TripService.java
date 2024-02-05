package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.TripsDto;
import com.vahner.airticketsapp.entity.Trips;

import java.util.List;
import java.util.UUID;

public interface TripService {
    Trips getTripsById(UUID uud);
    List<Trips> getTrips();
    Trips createTrip(TripsDto tripsDto);
}
