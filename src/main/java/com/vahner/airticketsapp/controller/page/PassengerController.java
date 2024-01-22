package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.entity.Passenger;
import com.vahner.airticketsapp.service.interf.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/passengers")
@RestController
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable UUID id) {
        return passengerService.getPassengerById(id);
    }
    @GetMapping
    public List<Passenger> getAllPassenger() {
        return passengerService.getPassengers();
    }

    @PostMapping("createPassenger")
    public Passenger creatPassengers(Passenger passenger){
        return passengerService.create(passenger);
    }
}