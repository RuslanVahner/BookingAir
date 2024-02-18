package com.vahner.airticketsapp.dto;

import com.vahner.airticketsapp.entity.enums.AccountStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountDto {

    String uuid;
    @NotNull(message = "Login shouldn't be null")
    @Size(min = 6, max = 50, message = "Login should be not null and from 6 to 50 symbols")
    String login;
    @NotNull(message = "Password shouldn't be null")
    @Size(min = 6, max = 50, message = "Password should be not null and from 6 to 50 symbols")
    String password;
    String owner;
    AccountStatus status;
    BigDecimal balance;

    BigDecimal totalCost;
}