package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.entity.Passenger;

import java.util.List;
import java.util.UUID;

public interface PassengerService {
    Passenger getPassengerById(UUID id);
    List<Passenger> getPassengers();
    Passenger create(Passenger passenger);
}