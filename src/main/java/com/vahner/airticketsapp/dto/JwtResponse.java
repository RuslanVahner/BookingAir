package com.vahner.airticketsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private static final String TYPE = "Bearer";
    private String accessToken;
    private String refreshToken;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtResponse that = (JwtResponse) o;
        return Objects.equals(accessToken, that.accessToken)
                && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken);
    }
}