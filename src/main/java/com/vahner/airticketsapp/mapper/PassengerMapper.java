package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.entity.Passenger;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface PassengerMapper {

    PassengerDto toDtoPassenger (Passenger passenger);
    List<PassengerDto> toDtoList(List<Passenger> passengers);
}
