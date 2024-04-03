package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.dto.ReviewUpdateDTO;
import com.vahner.airticketsapp.entity.Review;
import com.vahner.airticketsapp.service.interf.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Review>> getReviewsByFlight(@PathVariable UUID flightId) {
        List<Review> reviews = reviewService.getReviewsByFlight(flightId);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createReview(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        Review createdReview = reviewService.createReview(reviewCreateDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/updateReview/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable UUID id, @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        Review updatedReview = reviewService.updateReview(id, reviewUpdateDTO);
        return ResponseEntity.ok().body(updatedReview);
    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
