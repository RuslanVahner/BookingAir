package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    List<Booking> getAllBookings();

    Booking getBookingById(UUID id);

    Booking createBooking(BookingCreateDTO bookingCreateDTO);

    Booking updateBooking(UUID id, Booking bookingDetails);

    void deleteBooking(UUID id);
}
