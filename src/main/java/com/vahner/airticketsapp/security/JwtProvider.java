package com.vahner.airticketsapp.security;

import com.vahner.airticketsapp.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.lang.NonNull;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Getter
@Component
public class JwtProvider {

    private SecretKey jwtAccessSecret;
    private SecretKey jwtRefreshSecret;

    @Value("${variables.jwtAccessSecret}")
    private String rawAccessSecret;

    @Value("${variables.jwtRefreshSecret}")
    private String rawRefreshSecret;

    @Value("${variables.jwtExpirationMS}")
    private long jwtRefreshExpirationMS;

    @PostConstruct
    public void init() {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawRefreshSecret));
    }

    public String generateAccessToken(@NonNull Account account) {
        final Date accessExpirationDate = Date.from(Instant.now().plusSeconds(600));

        return Jwts.builder().subject(account.getLogin())
                .expiration(accessExpirationDate)
                .signWith(jwtRefreshSecret, Jwts.SIG.HS512)
                .claim("role", account.getRole())
                .compact();
    }

    public String generateRefreshToken(@NonNull Account account) {
        final Date refreshExpirationDate = Date.from(Instant.now().plusMillis(jwtRefreshExpirationMS));

        return Jwts.builder()
                .subject(account.getLogin())
                .expiration(refreshExpirationDate)
                .signWith(jwtRefreshSecret, Jwts.SIG.HS512)
                .compact();
    }

    public boolean validateToken(@NonNull String token, @NonNull Key secKey) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) secKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException ex) {
            log.error("JWT validation failed: {}", ex.getMessage());
        }
        return false;
    }

    public Claims getClaims(@NonNull String token, @NonNull Key secKey) {
        return Jwts.parser()
                .verifyWith((SecretKey) secKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Claims getAccessClaims(@NonNull String accessToken) {
        return getClaims(accessToken, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String refreshToken) {
        return getClaims(refreshToken, jwtRefreshSecret);
    }
}
