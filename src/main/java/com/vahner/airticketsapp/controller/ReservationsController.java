package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.validation.interf.Uuid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag("Reservations Controller")
@Validated
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationsController {

//    ReservationsService reservationsService;
//
//    @GetMapping("/get/{id}")
//    @Operation(summary = "basic reservations get rest method by id",
//            description = "returns  reservations from database for given id",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = " Reservations found"),
//                    @ApiResponse(responseCode = "404", description = "No reservations was found with this id")
//            })
//
//    public ResponseEntity<ReservationsDto> getReservationById(@Uuid @PathVariable String id){
//        ReservationsDto reservationsDto = reservationsService.getReservationById(id);
//        return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
//    }
}
