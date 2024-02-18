package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.PassengerDto;

import java.util.List;
import java.util.UUID;

public interface PassengerService {
    PassengerDto getPassengerById(String uuid);

    List<PassengerDto> getPassengers();

    PassengerDto updatePassenger(UUID uuid, PassengerDto passengerDto);

    void deletePassengerById(String uuid);
}