package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.dto.ReviewUpdateDTO;
import com.vahner.airticketsapp.entity.Review;
import com.vahner.airticketsapp.mapper.ReviewMapper;
import com.vahner.airticketsapp.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for ReviewServiceImpl")
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private UUID flightId;
    private UUID reviewId;
    private Review review;
    private ReviewCreateDTO reviewCreateDTO;
    private ReviewUpdateDTO reviewUpdateDTO;

    @BeforeEach
    void setUp() {
        flightId = UUID.fromString("d5b877ac-cf47-43c6-8d6a-8d0978005b12");
        reviewId = UUID.fromString("1b2e3ad4-567f-48a1-9c2d-8d0978005b15");
        review = new Review();
        review.setId(reviewId);
        review.setId(flightId);
        review.setRating(5);
        review.setComment("Great flight");

        reviewCreateDTO = new ReviewCreateDTO();
        reviewCreateDTO.setFlightId(flightId);
        reviewCreateDTO.setRating(5);
        reviewCreateDTO.setComment("Great flight");

        reviewUpdateDTO = new ReviewUpdateDTO();
        reviewUpdateDTO.setRating(4);
        reviewUpdateDTO.setComment("Good flight");
    }

    @Test
    void getAllReviewsTest() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        List<Review> reviews = reviewService.getAllReviews();

        verify(reviewRepository).findAll();
        assertFalse(reviews.isEmpty());
    }

    @Test
    void getReviewsByFlightTest() {
        when(reviewRepository.findByFlightId(flightId)).thenReturn(List.of(review));

        List<Review> reviews = reviewService.getReviewsByFlight(flightId);

        verify(reviewRepository).findByFlightId(flightId);
        assertFalse(reviews.isEmpty());
    }

    @Test
    void createReviewTest() {
        when(reviewMapper.reviewCreateDto(any(ReviewCreateDTO.class))).thenReturn(review);
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review savedReview = reviewService.createReview(reviewCreateDTO);

        verify(reviewMapper).reviewCreateDto(any(ReviewCreateDTO.class));
        verify(reviewRepository).save(any(Review.class));
        assertNotNull(savedReview);
    }

    @Test
    void updateReviewTest() {
        when(reviewRepository.findById(reviewId)).thenReturn(java.util.Optional.of(review));
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        Review updatedReview = reviewService.updateReview(reviewId, reviewUpdateDTO);

        verify(reviewRepository).findById(reviewId);
        verify(reviewRepository).save(any(Review.class));
        assertNotNull(updatedReview);
    }

    @Test
    void deleteReviewTest() {
        when(reviewRepository.findById(reviewId)).thenReturn(java.util.Optional.of(review));
        doNothing().when(reviewRepository).delete(any(Review.class));

        reviewService.deleteReview(reviewId);

        verify(reviewRepository).findById(reviewId);
        verify(reviewRepository).delete(any(Review.class));
    }
}