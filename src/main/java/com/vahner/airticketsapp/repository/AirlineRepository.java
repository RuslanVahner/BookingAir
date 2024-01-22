package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface AirlineRepository extends JpaRepository<Airline,UUID> {
    Airline getAirlineById(UUID id);
}
