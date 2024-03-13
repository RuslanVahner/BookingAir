package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.dto.ShortPassengerDto;
import com.vahner.airticketsapp.entity.Passenger;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerDto toDto(Passenger passenger);

    Passenger toEntity(PassengerDto passengerDto);

    ShortPassengerDto toShortDto(Passenger passenger);

    @AfterMapping
    default void updateEntity(PassengerDto dto, @MappingTarget Passenger entity) {
        if (dto.getPhone() != null) {
            String phoneNumbersValidations = phoneNumberValidation(dto.getPhone());
            entity.setPhone(phoneNumbersValidations);
        }
    }

    default String phoneNumberValidation(String phone) {
        return phone.replaceAll("\\s+|-", "");
    }

}