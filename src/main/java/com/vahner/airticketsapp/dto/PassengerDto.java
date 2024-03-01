package com.vahner.airticketsapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class PassengerDto {
    @NotBlank(message = "First name shouldn't be null")
    @Size(min = 3, max = 50, message = "First name should be not null and from 3 to 50 symbols")
    String firstName;
    @NotBlank(message = "Last name shouldn't be null")
    @Size(min = 3, max = 50, message = "Last name should be not null and from 3 to 50 symbols")
    String lastName;
    @Email
    String email;
    String phone;
}