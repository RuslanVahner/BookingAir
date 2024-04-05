package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.dto.ReviewUpdateDTO;
import com.vahner.airticketsapp.entity.Review;
import com.vahner.airticketsapp.service.interf.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Tag(name = "Review Controller")
@Validated
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    @Operation(summary = "get all reviews",
            description = "retrieve a list of all reviews",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully returned list of reviews")
            })
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/review/{flightId}")
    @Operation(summary = "get a flight by id",
            description = "returns a flight from the database for the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flight found"),
                    @ApiResponse(responseCode = "404", description = "No flight was found with this id")
            })
    public ResponseEntity<List<Review>> getReviewsByFlight(@PathVariable UUID flightId) {
        List<Review> reviews = reviewService.getReviewsByFlight(flightId);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping("/createReview")
    @Operation(summary = "create new review",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "create a new review with flightId, rating and comment",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReviewCreateDTO.class)))
    )
    public ResponseEntity<Review> createReview(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        Review createdReview = reviewService.createReview(reviewCreateDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/updateReview/{id}")
    @Operation(summary = "update review",
            description = "updates the information of an existing review",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully updated the review")
            })
    public ResponseEntity<Review> updateReview(@PathVariable UUID id, @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        Review updatedReview = reviewService.updateReview(id, reviewUpdateDTO);
        return ResponseEntity.ok().body(updatedReview);
    }

    @DeleteMapping("/deleteReview/{id}")
    @Operation(summary = "delete review",
            description = "deletion of an review by the specified id")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the review")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
