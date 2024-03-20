package com.vahner.airticketsapp.generator;

import com.vahner.airticketsapp.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.lang.NonNull;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
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
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }


//    @Value("${variables.jwtExpirationMS}")
//    private long jwtRefreshExpirationMS;

//    @PostConstruct
//    public void init() {
//        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawAccessSecret));
//        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawRefreshSecret));
//    }



    public String generateAccessToken(@lombok.NonNull Account account) {
        final Instant accessExpirationInstant = LocalDateTime.now()
                .plusMinutes(10)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .subject(account.getLogin())
                .expiration(Date.from(accessExpirationInstant))
                .signWith(jwtAccessSecret, Jwts.SIG.HS512)
                .claim("roles", account.getRole())
                .claim("login", account.getLogin())
                .compact();
    }

    public String generateRefreshToken(@lombok.NonNull Account account) {
        final Instant refreshExpirationInstant = LocalDateTime.now()
                .plusDays(30)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .subject(account.getLogin())
                .expiration(Date.from(refreshExpirationInstant))
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

    public boolean validateAccessToken(@lombok.NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@lombok.NonNull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }
}
