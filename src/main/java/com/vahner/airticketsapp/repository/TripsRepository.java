package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripsRepository extends JpaRepository<Trips, UUID> {

}
