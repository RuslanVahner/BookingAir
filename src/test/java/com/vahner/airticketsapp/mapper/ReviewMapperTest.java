package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class for ReviewMapper")
class ReviewMapperTest {

    private ReviewMapper reviewMapper;

    @BeforeEach
    public void setup(){
        reviewMapper = Mappers.getMapper(ReviewMapper.class);
    }

    @Test
    void reviewCreateDto() {
        UUID flightId = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5111");
        Flight flight = new Flight();
        flight.setId(flightId);

        ReviewCreateDTO dto = new ReviewCreateDTO();
        dto.setRating(5);
        dto.setComment("Great flight, amazing service!");

        Review review = new Review();
        review.setFlight(flight);

        assertNotNull(review.getFlight());
        assertEquals(flightId, review.getFlight().getId());
    }
}