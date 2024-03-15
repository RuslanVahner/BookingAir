package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.FlightDto;
import com.vahner.airticketsapp.entity.Flight;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FightMapper {

    FlightDto toShortDto(Flight flight);

    List<FlightDto> toEnti(List<Flight> flights);
}
