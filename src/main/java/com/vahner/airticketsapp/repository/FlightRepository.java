package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDate(String departureAirport, String arrivalAirport,
                                                                         LocalDate departureDate);
}
