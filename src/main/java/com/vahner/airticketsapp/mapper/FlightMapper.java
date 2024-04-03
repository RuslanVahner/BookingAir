package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper{

    Flight flightCreateDto(FlightCreateDTO flightCreateDTO);
}
