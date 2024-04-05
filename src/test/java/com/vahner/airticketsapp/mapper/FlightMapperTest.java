package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.dto.FlightUpdateDTO;
import com.vahner.airticketsapp.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDateTime;
import java.time.Month;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Test class for FlightMapper")
class FlightMapperTest {

    private FlightMapper flightMapper;

    @BeforeEach
    public void setup(){
        flightMapper = Mappers.getMapper(FlightMapper.class);
    }

    @Test
    void flightCreateDto() {
        FlightCreateDTO dto = new FlightCreateDTO();
        dto.setFlightNumber("AA123");
        dto.setDepartureAirport("JFK");
        dto.setArrivalAirport("LAX");
        dto.setDepartureTime(LocalDateTime.of(2024, Month.JANUARY, 29, 19, 30));
        dto.setArrivalTime(LocalDateTime.of(2024, Month.JANUARY, 29, 21, 45));
        dto.setPrice(350.00);

        Flight flight = flightMapper.flightCreateDto(dto);

        assertEquals(dto.getFlightNumber(), flight.getFlightNumber());
        assertEquals(dto.getDepartureAirport(), flight.getDepartureAirport());
        assertEquals(dto.getArrivalAirport(), flight.getArrivalAirport());
        assertEquals(dto.getDepartureTime(), flight.getDepartureTime());
        assertEquals(dto.getArrivalTime(), flight.getArrivalTime());
        assertEquals(dto.getPrice(), flight.getPrice());
    }

    @Test
    void updateFlightFromDtoTest() {

        FlightUpdateDTO dto = new FlightUpdateDTO();
        dto.setFlightNumber("FL123");
        dto.setDepartureAirport("Airport A");
        dto.setArrivalAirport("Airport B");
        dto.setDepartureTime(LocalDateTime.of(2023, 10, 10, 12, 0));
        dto.setArrivalTime(LocalDateTime.of(2023, 10, 10, 15, 0));
        dto.setPrice(200.0);

        Flight flight = new Flight();
        flight.setFlightNumber("FL000");
        flight.setDepartureAirport("Old Airport A");
        flight.setArrivalAirport("Old Airport B");
        flight.setDepartureTime(LocalDateTime.of(2023, 9, 9, 12, 0));
        flight.setArrivalTime(LocalDateTime.of(2023, 9, 9, 15, 0));
        flight.setPrice(100.0);

        flightMapper.updateFlightFromDto(dto,flight);

        assertEquals(dto.getFlightNumber(), flight.getFlightNumber());
        assertEquals(dto.getDepartureAirport(), flight.getDepartureAirport());
        assertEquals(dto.getArrivalAirport(), flight.getArrivalAirport());
        assertEquals(dto.getDepartureTime(), flight.getDepartureTime());
        assertEquals(dto.getArrivalTime(), flight.getArrivalTime());
        assertEquals(dto.getPrice(), flight.getPrice());
    }
}