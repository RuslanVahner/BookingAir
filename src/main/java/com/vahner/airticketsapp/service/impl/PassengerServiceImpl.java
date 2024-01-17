package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Passenger;
import com.vahner.airticketsapp.repository.PassengerRepository;
import com.vahner.airticketsapp.service.interf.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public Passenger getPassengerById(UUID id) {
        return passengerRepository.getReferenceById(id);
    }

    public List<Passenger> getPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger create(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}