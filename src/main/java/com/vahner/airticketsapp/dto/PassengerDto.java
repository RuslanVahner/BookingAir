package com.vahner.airticketsapp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

    @NotNull(message = "First name shouldn't be null")
    @Size(min = 3, max = 50, message = "First name should be not null and from 3 to 50 symbols")
    String firstName;
    @NotNull(message = "Last name shouldn't be null")
    @Size(min = 3, max = 50, message = "Last name should be not null and from 3 to 50 symbols")
    String lastName;
    @NotNull(message = "Password shouldn't be null")
    @Size(min = 6, max = 50, message = "Password should be not null and from 6 to 50 symbols")
    String password;
    String email;
    String phone;
    int age;
}