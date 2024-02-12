package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.entity.Passenger;
import com.vahner.airticketsapp.exception.PassengerNotFoundException;
import com.vahner.airticketsapp.mapper.PassengerMapper;
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
    private final PassengerMapper passengerMapper;

    @Override
    public PassengerDto getPassengerById(String uuid) {
        Passenger passenger = passengerRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new PassengerNotFoundException("Passenger is not found."));
        return passengerMapper.toDtoPassenger(passenger);
    }

    @Override
    public List<PassengerDto> getPassengers() {
        return passengerMapper.toDtoList(passengerRepository.findAll());
    }

    @Override
    public PassengerDto updatePassenger(UUID uuid,PassengerDto passengerDto) {
            Passenger  existingPassenger = passengerRepository.findById(uuid)
                    .orElseThrow(() -> new PassengerNotFoundException("Passenger id is not found"));

            existingPassenger.setEmail(passengerDto.getEmail());
            existingPassenger.setPhone(passengerDto.getPhone());
            existingPassenger.setLastName(passengerDto.getFirstName());
            existingPassenger.setAge(passengerDto.getAge());

            Passenger updatePassenger = passengerRepository.save(existingPassenger);
            return passengerMapper.toDtoPassenger(updatePassenger);
    }

    @Override
    public void deletePassengerById(String uuid){
        Passenger passenger = passengerRepository.findById(UUID.fromString(uuid))
                .orElseThrow(()-> new PassengerNotFoundException("Passenger with id not found"));
        passengerRepository.delete(passenger);
    }
}