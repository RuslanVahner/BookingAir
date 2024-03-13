package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.exception.AuthException;
import com.vahner.airticketsapp.exception.ErrorMessage;
import com.vahner.airticketsapp.repository.RefreshTokenRepository;
import com.vahner.airticketsapp.security.JwtAuthentication;
import com.vahner.airticketsapp.dto.JwtRequest;
import com.vahner.airticketsapp.dto.JwtResponse;
import com.vahner.airticketsapp.security.JwtProvider;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.service.interf.AuthService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountService accountService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtUtils;

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final Account account = accountService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
        if (account.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtUtils.generateAccessToken(account);
            final String refreshToken = jwtUtils.generateRefreshToken(account);
            refreshTokenRepository.save(account.getLogin(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        }
        throw new AuthException(ErrorMessage.M_WRONG_CREDENTIALS);
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtUtils.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtUtils.getRefreshClaims(refreshToken);
            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Account account = accountService.getByLogin(claims.getSubject())
                        .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
                return new JwtResponse(jwtUtils.generateAccessToken(account), null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtUtils.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtUtils.getRefreshClaims(refreshToken);
            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Account account = accountService.getByLogin(claims.getSubject())
                        .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
                final String newRefreshToken = jwtUtils.generateRefreshToken(account);
                refreshTokenRepository.save(account.getLogin(), newRefreshToken);
                return new JwtResponse(jwtUtils.generateAccessToken(account), newRefreshToken);
            }
        }
        throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}