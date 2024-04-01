package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.JwtRequest;
import com.vahner.airticketsapp.dto.JwtResponse;
import com.vahner.airticketsapp.dto.RefreshJwtRequest;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.generator.JwtProvider;
import com.vahner.airticketsapp.repository.RefreshTokenRepository;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.service.interf.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = (Account) authentication.getPrincipal();

        String accessToken = jwtProvider.generateAccessToken(account);
        String refreshToken = jwtProvider.generateRefreshToken(account);

        refreshTokenRepository.save(account.getLogin(), refreshToken);

        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestParam("refreshToken") String refreshToken) {
        return ResponseEntity.ok(authService.refreshTokens(refreshToken));
    }



    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Account signupRequest) {
        if (accountService.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different login");
        }
       Account account = new Account();
        account.setLogin(signupRequest.getLogin());
        account.setBalance(BigDecimal.ZERO);
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        account.setCreateAccountDate(LocalDate.now());
        account.setOwner(signupRequest.getLogin());
        accountService.save(account);
        return ResponseEntity.ok("Account created");
    }

    @PostMapping("/signing")
    public ResponseEntity<?> signing(@RequestBody JwtRequest jwtRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getLogin(), jwtRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Account account = getAccountFromAuthentication(authentication);
            String accessToken = jwtProvider.generateAccessToken(account);
            String refreshToken = jwtProvider.generateRefreshToken(account);

            JwtResponse jwtResponse = new JwtResponse();
            jwtResponse.setAccessToken(accessToken);
            jwtResponse.setRefreshToken(refreshToken);

            return ResponseEntity.ok(jwtResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private Account getAccountFromAuthentication(Authentication authentication) {
        return null;
    }

}