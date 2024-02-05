package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trips, UUID> {
    @Query("SELECT t FROM Trips t WHERE t.nameTrips = :nameTrips")
    Trips gerTripsByNmeTrips(@Param("nameTrips") String nameTrips);

}