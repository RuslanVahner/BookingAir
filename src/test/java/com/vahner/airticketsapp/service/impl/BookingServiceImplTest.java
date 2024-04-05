package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.Booking;
import com.vahner.airticketsapp.mapper.BookingMapper;
import com.vahner.airticketsapp.repository.BookingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for BookingServiceImpl")
class BookingServiceImplTest {

    @Mock
    private Validator validator;
    @Mock
    private BookingMapper bookingMapper;
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;


    @Test
    void shouldReturnAllBookings() {
        Booking booking = new Booking();
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(booking));

        List<Booking> result = bookingService.getAllBookings();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(bookingRepository).findAll();
    }


    @Test
    void shouldReturnBookingById() {
        UUID id = UUID.fromString("333eefab-5efd-414d-9874-d8719fdbc111");
        Booking booking = new Booking();
        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));

        Booking result = bookingService.getBookingById(id);

        assertNotNull(result);
        assertEquals(booking, result);
        verify(bookingRepository).findById(id);
    }

//    @Test
//    void shouldCreateBooking() {
//        BookingCreateDTO dto = new BookingCreateDTO();
//        Booking booking = new Booking();
//        when(bookingMapper.bookingCreateDto(dto)).thenReturn(booking);
//        when(bookingRepository.save(booking)).thenReturn(booking);
//
//        Booking result = bookingService.createBooking(dto);
//
//        assertNotNull(result);
//        assertEquals(booking, result);
//        verify(bookingMapper).bookingCreateDto(dto);
//        verify(bookingRepository).save(booking);
//    }

    @Test
    void shouldUpdateBooking() {
        UUID id = UUID.fromString("333eefab-5efd-414d-9874-d8719fdbc777");
        Booking existingBooking = new Booking();
        Booking updatedDetails = new Booking();
        when(bookingRepository.findById(id)).thenReturn(Optional.of(existingBooking));
        when(bookingRepository.save(existingBooking)).thenReturn(existingBooking);

        Booking result = bookingService.updateBooking(id, updatedDetails);

        assertNotNull(result);
        assertEquals(existingBooking.getStatus(), updatedDetails.getStatus());
        verify(bookingRepository).save(existingBooking);
    }


    @Test
    void shouldDeleteBooking() {
        UUID id = UUID.fromString("333eefab-5efd-414d-9874-d8719fdbc555");
        Booking booking = new Booking();
        when(bookingRepository.findById(id)).thenReturn(Optional.of(booking));
        doNothing().when(bookingRepository).delete(booking);

        bookingService.deleteBooking(id);

        verify(bookingRepository).delete(booking);
    }
}