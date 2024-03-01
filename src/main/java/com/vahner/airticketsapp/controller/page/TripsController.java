package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.dto.TripsDto;
import com.vahner.airticketsapp.service.interf.TripsService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@Controller
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripsController {

    private final TripsService tripsService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public TripsDto getTripsById(@Uuid @PathVariable("uuid") String uuid) {
        return tripsService.getTripsById(uuid);
    }

    @GetMapping
    public ResponseEntity<List<TripsDto>> fidAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tripsService.findAll().getBody());
    }

    @PostMapping("/createTrips")
    public ResponseEntity<TripsDto> create(@RequestBody TripsDto tripsDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tripsService.create(tripsDto).getBody());
    }

    @PutMapping("/updateTrips/{uuid}")
    public TripsDto updateTrips(@PathVariable String uuid, @RequestBody TripsDto tripsDto) {
        return tripsService.updateTrips(uuid, tripsDto);
    }

    @DeleteMapping("/deleteTrips/{uuid}")
    public ResponseEntity<Void> deleteTrips(@PathVariable("uuid") String uuid) {
        tripsService.deleteTrips(String.valueOf(UUID.fromString(uuid)));
        return ResponseEntity.noContent().build();
    }

}