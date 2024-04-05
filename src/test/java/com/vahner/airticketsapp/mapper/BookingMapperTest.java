package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.AppUser;
import com.vahner.airticketsapp.entity.Booking;
import com.vahner.airticketsapp.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Test class for BookingMapper")
class BookingMapperTest {

    private BookingMapper bookingMapper;

    @BeforeEach
    public void setUp() {
       bookingMapper = Mappers.getMapper(BookingMapper.class);
    }
    @Test
    void bookingCreateDto() {
        UUID userId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc333");
        UUID flightId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc777");

        BookingCreateDTO dto = new BookingCreateDTO();
        dto.setUserId(userId);
        dto.setFlightId(flightId);

        AppUser user = new AppUser();
        user.setId(userId);

        Flight flight = new Flight();
        flight.setId(flightId);

        Booking booking = new Booking();
        booking.setAppUser(user);
        booking.setFlight(flight);

        assertEquals(dto.getUserId(), booking.getAppUser().getId());
        assertEquals(dto.getFlightId(), booking.getFlight().getId());
    }

}