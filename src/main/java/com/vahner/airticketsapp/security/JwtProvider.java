package com.vahner.airticketsapp.security;

import com.vahner.airticketsapp.entity.Account;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Getter
@Component
public class JwtProvider {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;

    @Autowired
    public JwtProvider(
            @Value("${JWT.SECRET.ACCESS}") String jwtAccessSecret,
            @Value("${JWT.SECRET.REFRESH}") String jwtRefreshSecret
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessToken(@NonNull Account account) {
        final Instant accessExpirationInstant = LocalDateTime.now()
                .plusMinutes(10)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .subject(account.getLogin())
                .expiration(Date.from(accessExpirationInstant))
                .signWith(jwtAccessSecret)
                .claim("role", account.getRole())
                .claim("login", account.getLogin())
                .compact();
    }

    public String generateRefreshToken(@NonNull Account account) {
        final Instant refreshExpirationInstant = LocalDateTime.now()
                .plusDays(30)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .subject(account.getLogin())
                .expiration(Date.from(refreshExpirationInstant))
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateToken(@NonNull String token, @NonNull Key seKey) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) seKey)
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
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