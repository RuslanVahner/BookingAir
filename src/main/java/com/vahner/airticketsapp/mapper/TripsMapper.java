package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.TripsDto;
import com.vahner.airticketsapp.entity.Trips;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TripsMapper {

     Trips toEntity(TripsDto tripsDto);

     TripsDto toDto(Trips trips);

     List<TripsDto> toDtoList(List<Trips> trips);

    TripsDto toTripsEntity(TripsDto tripsDto);
}
