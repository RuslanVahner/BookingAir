package com.vahner.airticketsapp.dto;

import com.vahner.airticketsapp.validation.interf.Login;
import com.vahner.airticketsapp.validation.interf.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @Login
    @NotBlank(message = "Login shouldn't be null")
    String login;
    @Password
    @NotBlank(message = "Password shouldn't be null")
    String password;

}
