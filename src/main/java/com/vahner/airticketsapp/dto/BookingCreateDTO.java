package com.vahner.airticketsapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreateDTO {
    @NotNull(message = "User ID cannot be null")
    private UUID userId;

    @NotNull(message = "Flight ID cannot be null")
    private UUID flightId;
}
