package com.vahner.airticketsapp.generator;

import com.vahner.airticketsapp.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.lang.NonNull;
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

    private static final String CLAIM_ROLE = "role";
    private static final String CLAIM_LOGIN = "login";

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;
    private final long jwtRefreshExpirationMS;

    @Autowired
    public JwtProvider(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret,
            @Value("${jwt.expirations}") Long jwtRefreshExpirationMS
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
        this.jwtRefreshExpirationMS = jwtRefreshExpirationMS;
    }

    public String generateAccessToken(@lombok.NonNull Account account) {
        final Instant accessExpirationInstant = LocalDateTime.now()
                .plusMinutes(10)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .subject(account.getLogin())
                .expiration(Date.from(accessExpirationInstant))
                .signWith(jwtAccessSecret, Jwts.SIG.HS512)
                .claim(CLAIM_ROLE, account.getRole())
                .claim(CLAIM_LOGIN, account.getLogin())
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

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode((CharSequence) jwtAccessSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtAccessSecret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateAccessToken(@lombok.NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@lombok.NonNull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }
}
