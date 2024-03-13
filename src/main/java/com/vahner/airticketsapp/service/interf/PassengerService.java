package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.dto.ShortPassengerDto;

import java.util.List;

public interface PassengerService {
    PassengerDto getPassengerById(String id);

    List<ShortPassengerDto> getAllPassengers();

    PassengerDto createPassenger(PassengerDto passengerDto);

    void updatePassenger(String id, PassengerDto passengerDto);

    void deletePassengerById(String id);
}