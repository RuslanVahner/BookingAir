package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.JwtRequest;
import com.vahner.airticketsapp.dto.JwtResponse;
import com.vahner.airticketsapp.dto.RefreshJwtRequest;
import com.vahner.airticketsapp.service.interf.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("login")
    public JwtResponse authorisation(@RequestBody JwtRequest jwtRequest) {
        return service.login(jwtRequest);
    }

    @PostMapping("new-token")
    public JwtResponse getNewAccessToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        return service.getAccessToken(refreshJwtRequest.getRefreshToken());
    }

    @PostMapping("refresh")
    public JwtResponse refreshJwtTokens(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        return service.refresh(refreshJwtRequest.getRefreshToken());
    }
}