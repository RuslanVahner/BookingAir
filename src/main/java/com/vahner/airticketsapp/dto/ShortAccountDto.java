package com.vahner.airticketsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ShortAccountDto {
    String id;

    @NotBlank(message = "Login shouldn't be null")
    @Size(min = 5, max = 50, message = "Login should be not null and from 6 to 50 symbols")
    String login;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    String createAccountDAte;

    BigDecimal balance;

}