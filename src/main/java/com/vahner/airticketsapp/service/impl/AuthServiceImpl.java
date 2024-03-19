package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.JwtRequest;
import com.vahner.airticketsapp.dto.JwtResponse;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.RefreshToken;
import com.vahner.airticketsapp.exception.AuthException;
import com.vahner.airticketsapp.exception.ErrorMessage;
import com.vahner.airticketsapp.repository.RefreshTokenRepository;
import com.vahner.airticketsapp.security.JwtAuthentication;
import com.vahner.airticketsapp.security.JwtProvider;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.service.interf.AuthService;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountService accountService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final Account account = accountService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
        if (passwordEncoder.matches(authRequest.getPassword(), account.getPassword())) {
            final String refreshToken = jwtUtils.generateRefreshToken(account);
            refreshTokenRepository.save(new RefreshToken(account.getLogin(), refreshToken));
            return new JwtResponse(jwtUtils.generateAccessToken(account), refreshToken);
        } else {
            throw new AuthException(ErrorMessage.M_WRONG_CREDENTIALS);
        }
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken, jwtUtils.getJwtRefreshSecret())) {
            throw new AuthException("Invalid refresh token.");
        }

        Claims claims = jwtUtils.getRefreshClaims(refreshToken);
        String username = claims.getSubject();
        Account account = accountService.getByLogin(username)
                .orElseThrow(() -> new AuthException("Account not found."));

        String newAccessToken = jwtUtils.generateAccessToken(account);
        return new JwtResponse(newAccessToken, refreshToken);
    }

    @Override
    @Transactional
    public JwtResponse refresh(@NonNull String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken, jwtUtils.getJwtRefreshSecret())) {
            throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
        }
        Claims claims = jwtUtils.getRefreshClaims(refreshToken);
        String login = claims.getSubject();
        Account account = accountService.getByLogin(login)
                .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
        String newRefreshToken = jwtUtils.generateRefreshToken(account);

        refreshTokenRepository.save(new RefreshToken(login, newRefreshToken));

        String newAccessToken = jwtUtils.generateAccessToken(account);
        return new JwtResponse(newAccessToken, newRefreshToken);
    }


    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public void updateToken(String login, String newRefreshToken) {
        refreshTokenRepository.save(new RefreshToken(login, newRefreshToken));
    }

    private Claims validateAndGetClaims(String token) {
        if (!jwtUtils.validateToken(token, jwtUtils.getJwtRefreshSecret())) {
            throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
        }
        return jwtUtils.getRefreshClaims(token);
    }

}
