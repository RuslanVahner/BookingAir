package com.vahner.airticketsapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor

public class AccountDto {
    String uuid;
    @NotBlank(message = "Login shouldn't be null")
    @Size(min = 6, max = 50, message = "Login should be not null and from 6 to 50 symbols")
    String login;
    @NotBlank(message = "Password shouldn't be null")
    @Size(min = 6, max = 50, message = "Password should be not null and from 6 to 50 symbols")
    String password;
    String status;
    BigDecimal balance;

    @Email
    String email;
    String owner;
    String phone;
}