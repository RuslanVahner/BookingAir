package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassengerRepository extends JpaRepository<Passenger, UUID> {

    Passenger findByFirstName(String username);
}