package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Trips;
import com.vahner.airticketsapp.repository.TripRepository;
import com.vahner.airticketsapp.service.interf.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Override
    public Trips getTripsById(UUID id){
        return tripRepository.getReferenceById(id);
    }

    @Override
    public List<Trips> getTrips(){
        return  tripRepository.findAll();
    }

    @Override
    public Trips createTrip(Trips trips){
        return tripRepository.save(trips);
    }
}