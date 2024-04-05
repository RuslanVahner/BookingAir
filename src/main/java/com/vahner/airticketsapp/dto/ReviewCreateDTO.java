package com.vahner.airticketsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateDTO {
    private UUID flightId;
    private int rating;
    private String comment;
}
