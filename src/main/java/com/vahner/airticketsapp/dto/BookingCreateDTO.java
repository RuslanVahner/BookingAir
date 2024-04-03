package com.vahner.airticketsapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookingCreateDTO {
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Flight ID cannot be null")
    private UUID flightId;
}
