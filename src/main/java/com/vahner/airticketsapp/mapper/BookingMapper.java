package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.entity.Booking;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.service.interf.AppUserService;
import com.vahner.airticketsapp.service.interf.FlightService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {AppUserService.class, FlightService.class})
public interface BookingMapper {

//    @Mapping(target = "appUser", source = "userId", qualifiedByName = "userIdToAppUser")
//    @Mapping(target = "flight", source = "flightId", qualifiedByName = "flightIdToFlight")
    Booking bookingCreateDto(BookingCreateDTO bookingCreateDTO);

    @Named("userIdToAppUser")
    default AppUser userIdToUser(UUID userId, @Context AppUserService userService) {
        return userService.getUserById(userId);
    }

    @Named("flightIdToFlight")
    default Flight flightIdToFlight(UUID flightId, @Context FlightService flightService) {
        return flightService.getFlightById(flightId);
    }
}
