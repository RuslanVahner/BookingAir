package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.JwtResponse;

public interface AuthService {

    JwtResponse refreshTokens(String refreshToken);


}