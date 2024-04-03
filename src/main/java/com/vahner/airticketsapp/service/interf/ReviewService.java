package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.dto.ReviewUpdateDTO;
import com.vahner.airticketsapp.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    List<Review> getAllReviews();

    List<Review> getReviewsByFlight(UUID flightId);

    Review createReview(ReviewCreateDTO reviewCreateDTO);

    Review updateReview(UUID id, ReviewUpdateDTO reviewUpdateDTO);

    void deleteReview(UUID id);
}
