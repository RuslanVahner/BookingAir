package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.security.JwtAuthentication;
import com.vahner.airticketsapp.security.JwtRequest;
import com.vahner.airticketsapp.security.JwtResponse;
import lombok.NonNull;

public interface AuthService {
    JwtResponse login(@NonNull JwtRequest authRequest);

    JwtResponse getAccessToken(@NonNull String refreshToken);

    JwtResponse refresh(@NonNull String refreshToken);

    JwtAuthentication getAuthInfo();
}
