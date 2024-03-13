package com.vahner.airticketsapp.dto;

import com.vahner.airticketsapp.entity.enums.AccountStatus;
import com.vahner.airticketsapp.validation.interf.Login;
import com.vahner.airticketsapp.validation.interf.Password;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    String accountId;

    @Login
    @NotBlank(message = "Login shouldn't be null")
    @Size(min = 5, max = 50, message = "Login should be not null and from 5 to 50 symbols")
    String login;

    @Password
    @NotBlank(message = "Password shouldn't be null")
    @Size(min = 5, message = "Password should be not null and from 5 symbols")
    String password;

    BigDecimal balance;

    AccountStatus status;

}