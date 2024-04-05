package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.Booking;
import com.vahner.airticketsapp.mapper.BookingMapper;
import com.vahner.airticketsapp.repository.BookingRepository;
import com.vahner.airticketsapp.service.interf.BookingService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final Validator validator;
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        log.info("Retrieving all bookings");
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(UUID id) {
        log.info("Retrieving booking with ID: {}", id);
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }

    @Override
    public Booking createBooking(BookingCreateDTO bookingCreateDTO) {
        log.info("Creating new booking with userId: {} and flightId: {}", bookingCreateDTO.getUserId(), bookingCreateDTO.getFlightId());
        validateBookingCreateDTO(bookingCreateDTO);
        Booking booking = bookingMapper.bookingCreateDto(bookingCreateDTO);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(UUID id, Booking bookingDetails) {
        log.info("Updating booking with ID: {}", id);
        Booking booking = getBookingById(id);
        booking.setStatus(bookingDetails.getStatus());
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(UUID id) {
        log.info("Deleting booking with ID: {}", id);
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }

    private void validateBookingCreateDTO(BookingCreateDTO bookingCreateDTO) {
        Set<ConstraintViolation<BookingCreateDTO>> violations = validator.validate(bookingCreateDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
