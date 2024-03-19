package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.FlightDto;
import com.vahner.airticketsapp.entity.Flight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDto toDto(Flight flight);
}
