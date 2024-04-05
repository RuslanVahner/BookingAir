package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.dto.FlightUpdateDTO;
import com.vahner.airticketsapp.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    Flight flightCreateDto(FlightCreateDTO flightCreateDTO);

    @Mapping(target = "flightNumber", source = "flightNumber")
    @Mapping(target = "departureAirport", source = "departureAirport")
    @Mapping(target = "arrivalAirport", source = "arrivalAirport")
    @Mapping(target = "departureTime", source = "departureTime")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    @Mapping(target = "price", source = "price")
    void updateFlightFromDto(FlightUpdateDTO flightUpdateDTO, @MappingTarget Flight flight);
}
