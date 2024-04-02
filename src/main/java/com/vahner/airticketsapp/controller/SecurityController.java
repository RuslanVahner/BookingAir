package com.vahner.airticketsapp.controller;



import com.vahner.airticketsapp.dto.SignupRequest;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.service.interf.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

//@Tag(name = "Security Controller")
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class SecurityController {
//
//    private final JwtProvider jwtProvider;
//    private final AccountService accountService;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
//        if (accountService.existsByLogin(signupRequest.getLogin())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different login");
//        }
//        CreatAccountDto create = new CreatAccountDto();
//        create.setLogin(signupRequest.getLogin());
//        create.setBalance(BigDecimal.ZERO);
//        create.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//        accountService.save(create);
//        return ResponseEntity.ok("Account created");
//    }
//
//    @PostMapping("/signing")
//    public ResponseEntity<?> signing(@RequestBody JwtRequest jwtRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(jwtRequest.getLogin(), jwtRequest.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            Account account = getAccountFromAuthentication(authentication);
//            String accessToken = jwtProvider.generateAccessToken(account);
//            String refreshToken = jwtProvider.generateRefreshToken(account);
//
//            JwtResponse jwtResponse = new JwtResponse();
//            jwtResponse.setAccessToken(accessToken);
//            jwtResponse.setRefreshToken(refreshToken);
//
//            return ResponseEntity.ok(jwtResponse);
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//
//}
