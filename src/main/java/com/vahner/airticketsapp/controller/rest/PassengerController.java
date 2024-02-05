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
    public ResponseEntity<PassengerDto> getPassengerById(@Uuid @PathVariable("uuid") String uuid) {
        PassengerDto passengerDto = passengerService.getPassengerById(uuid);
        return ResponseEntity.ok(passengerDto);
    }

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllPassenger(){
        List<PassengerDto> passengerDtoList = passengerService.getPassengers();
        return ResponseEntity.ok(passengerDtoList);
    }

    @PutMapping("updetaPassenger/{uuid}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable UUID uuid, @RequestBody PassengerDto passengerDto){
        PassengerDto updatePassenger = passengerService.updatePassenger(uuid,passengerDto);
        return ResponseEntity.ok(updatePassenger);
    }

}