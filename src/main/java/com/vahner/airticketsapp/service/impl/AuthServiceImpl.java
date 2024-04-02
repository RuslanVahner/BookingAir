package com.vahner.airticketsapp.service.impl;

//import com.vahner.airticketsapp.dto.JwtResponse;
//import com.vahner.airticketsapp.entity.Account;
//import com.vahner.airticketsapp.exception.AuthException;
//import com.vahner.airticketsapp.exception.ErrorMessage;
//import com.vahner.airticketsapp.repository.AccountRepository;
//import com.vahner.airticketsapp.repository.RefreshTokenRepository;
//import com.vahner.airticketsapp.service.interf.AuthService;
//import io.jsonwebtoken.Claims;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final JwtProvider jwtProvider;
//    private final AccountRepository accountRepository;
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    @Override
//    public JwtResponse refreshTokens(String refreshToken) {
//        if (!jwtProvider.validateRefreshToken(refreshToken)) {
//            throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
//        }
//
//        Claims claims = jwtProvider.getClaims(refreshToken);
//        String login = claims.getSubject();
//        String savedRefreshToken = refreshTokenRepository.findById(login);
//
//        if (savedRefreshToken == null || !savedRefreshToken.equals(refreshToken)) {
//            throw new AuthException("Refresh token mismatch");
//        }
//
//        Account account = accountRepository.findByLogin(login)
//                .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
//
//        String newAccessToken = jwtProvider.generateAccessToken(account);
//        String newRefreshToken = jwtProvider.generateRefreshToken(account);
//        refreshTokenRepository.save(login, newRefreshToken);
//
//        return new JwtResponse(newAccessToken, newRefreshToken);
//    }
//}