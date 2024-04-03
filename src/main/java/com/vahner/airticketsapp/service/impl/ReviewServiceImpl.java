package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.dto.ReviewUpdateDTO;
import com.vahner.airticketsapp.entity.Review;
import com.vahner.airticketsapp.mapper.ReviewMapper;
import com.vahner.airticketsapp.repository.ReviewRepository;
import com.vahner.airticketsapp.service.interf.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<Review> getAllReviews() {
        log.info("Retrieving all reviews");
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByFlight(UUID flightId) {
        log.info("Retrieving reviews for flight ID: {}", flightId);
        List<Review> reviews = reviewRepository.findByFlightId(flightId);
        log.info("Found {} reviews for flight ID: {}", reviews.size(), flightId);
        return reviews;
    }

    @Override
    public Review createReview(ReviewCreateDTO reviewCreateDTO) {
        log.info("Creating review for flight ID: {}", reviewCreateDTO.getFlightId());
        Review review = reviewMapper.reviewCreateDto(reviewCreateDTO);
        Review savedReview = reviewRepository.save(review);
        log.info("Review created successfully with ID: {}", savedReview.getId());
        return savedReview;
    }

    @Override
    public Review updateReview(UUID id, ReviewUpdateDTO reviewUpdateDTO) {
        log.info("Updating review with ID: {}", id);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
        review.setRating(reviewUpdateDTO.getRating());
        review.setComment(reviewUpdateDTO.getComment());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(UUID id) {
        log.info("Deleting review with ID: {}", id);
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
        log.info("Review with ID: {} deleted successfully", id);
        reviewRepository.delete(review);
    }
}
