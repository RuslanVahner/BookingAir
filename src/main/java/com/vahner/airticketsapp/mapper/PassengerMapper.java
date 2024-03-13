package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.dto.ShortPassengerDto;
import com.vahner.airticketsapp.entity.Passenger;
import com.vahner.airticketsapp.entity.enums.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerDto toDto(Passenger passenger);

    Passenger toEntity(PassengerDto passengerDto);

    @Mapping(target = "id", expression = "java(account.getId().toString())")
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