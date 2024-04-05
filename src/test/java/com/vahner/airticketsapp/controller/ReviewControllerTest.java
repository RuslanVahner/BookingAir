package com.vahner.airticketsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vahner.airticketsapp.dto.ReviewCreateDTO;
import com.vahner.airticketsapp.dto.ReviewUpdateDTO;
import com.vahner.airticketsapp.entity.Review;
import com.vahner.airticketsapp.service.interf.ReviewService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Disabled("Test class for ReviewController")
@EntityScan("com.vahner.airticketsapp.entity")
@WebMvcTest(ReviewController.class)
@AutoConfigureMockMvc()
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setup() {
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(context)
                        .apply(springSecurity())
                        .build();
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void createReview() throws Exception {
        ReviewCreateDTO reviewCreateDTO = new ReviewCreateDTO();
        reviewCreateDTO.setFlightId(UUID.randomUUID());
        reviewCreateDTO.setRating(5);
        reviewCreateDTO.setComment("Great flight, smooth experience!");

        String reviewCreateDTOJson = objectMapper.writeValueAsString(reviewCreateDTO);

        mockMvc.perform(post("/api/reviews/createReview")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewCreateDTOJson))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void getAllReviews_ShouldReturnAllReviews() throws Exception {
        List<Review> reviewList = new ArrayList<>();
        Review review = new Review();
        review.setId(UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c"));
        review.setRating(5);
        review.setComment("Great service!");

        reviewList.add(review);

        when(reviewService.getAllReviews()).thenReturn(reviewList);

        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("0adeefab-5efd-414d-9874-d8719fdbc96c"))
                .andExpect(jsonPath("$[0].rating").value(5))
                .andExpect(jsonPath("$[0].comment").value("Great service!"));
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void updateReview_ShouldUpdateReview() throws Exception {
        UUID reviewId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c");
        ReviewUpdateDTO reviewUpdateDTO = new ReviewUpdateDTO();
        reviewUpdateDTO.setRating(4);
        reviewUpdateDTO.setComment("Updated comment");

        Review updatedReview = new Review();
        updatedReview.setId(reviewId);
        updatedReview.setRating(reviewUpdateDTO.getRating());
        updatedReview.setComment(reviewUpdateDTO.getComment());

        when(reviewService.updateReview(eq(reviewId), any(ReviewUpdateDTO.class))).thenReturn(updatedReview);

        mockMvc.perform(put("/api/reviews/updateReview/{id}", reviewId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(updatedReview.getRating()))
                .andExpect(jsonPath("$.comment").value(updatedReview.getComment()));
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void deleteReview_ShouldDeleteReview() throws Exception {
        UUID reviewId = UUID.fromString("0adeefab-5efd-414d-9874-d8719fdbc96c");

        doNothing().when(reviewService).deleteReview(reviewId);

        mockMvc.perform(delete("/api/reviews/deleteReview/{id}", reviewId)
                       .with(csrf()))
                .andExpect(status().isNoContent());

        verify(reviewService, times(1)).deleteReview(reviewId);
    }



}