package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.service.interf.PassengerService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/api/passengers")
@RestController
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDto getPassengerById(@Uuid @PathVariable("uuid") String uuid) {
        return passengerService.getPassengerById(uuid);
    }

    @GetMapping
    public List<PassengerDto> getAllPassenger() {
        return passengerService.getPassengers();
    }

    @PutMapping("updatePassenger/{uuid}")
    public PassengerDto updatePassenger(@PathVariable UUID uuid, @RequestBody PassengerDto passengerDto) {
        return passengerService.updatePassenger(uuid, passengerDto);
    }

}