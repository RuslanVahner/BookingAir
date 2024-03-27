package com.vahner.airticketsapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerDto {
    String passengerId;

    @NotBlank(message = "First name shouldn't be null")
    @Size(min = 3, max = 50, message = "First name should be not null and from 3 to 50 symbols")
    String firstName;

    @NotBlank(message = "Last name shouldn't be null")
    @Size(min = 3, max = 50, message = "Last name should be not null and from 3 to 50 symbols")
    String lastName;

    @Email(message = "Email should be valid")
    String email;

    int age;

    String phone;
}