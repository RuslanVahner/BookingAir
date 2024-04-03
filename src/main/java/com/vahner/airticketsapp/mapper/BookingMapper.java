package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking bookingCreateDto(BookingCreateDTO bookingCreateDTO);
}
