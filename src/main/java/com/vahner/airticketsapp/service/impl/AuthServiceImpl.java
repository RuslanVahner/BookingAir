package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.JwtRequest;
import com.vahner.airticketsapp.dto.JwtResponse;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.exception.AuthException;
import com.vahner.airticketsapp.exception.ErrorMessage;
import com.vahner.airticketsapp.repository.RefreshTokenRepository;
import com.vahner.airticketsapp.security.JwtAuthentication;
import com.vahner.airticketsapp.generator.JwtProvider;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.service.interf.AuthService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountService accountService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(@lombok.NonNull JwtRequest authRequest) {
        final Account account = accountService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
        if (account.getPassword().equals(authRequest.getPassword())) {
            final String refreshToken = jwtProvider.generateRefreshToken(account);
            refreshTokenRepository.save(account.getLogin(), refreshToken);
            return new JwtResponse(jwtProvider.generateAccessToken(account), refreshToken);
        }
        throw new AuthException(ErrorMessage.M_WRONG_CREDENTIALS);
    }

    @Override
    public JwtResponse getAccessToken(@lombok.NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Account account = accountService.getByLogin(claims.getSubject())
                        .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
                return new JwtResponse(jwtProvider.generateAccessToken(account), null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final Account account = accountService.getByLogin(claims.getSubject())
                        .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
                final String newRefreshToken = jwtProvider.generateRefreshToken(account);
                refreshTokenRepository.save(account.getLogin(), newRefreshToken);
                return new JwtResponse(jwtProvider.generateAccessToken(account), newRefreshToken);
            }
        }
        throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
