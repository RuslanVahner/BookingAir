package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.entity.Account;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/auth")
//@AllArgsConstructor
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@Valid @RequestBody JwtRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getLogin(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Account account = (Account) authentication.getPrincipal();
//
//        return ResponseEntity.ok("User " + account.getLogin() + " successfully logged in.");
//    }

//    @PostMapping("/refresh")
//    public ResponseEntity<JwtResponse> refresh(@RequestParam("refreshToken") String refreshToken) {
//        return ResponseEntity.ok(authService.refreshTokens(refreshToken));
//    }



//    @PostMapping("/signup")
//    public ResponseEntity<?> signup(@RequestBody Account signupRequest) {
//        if (accountService.existsByLogin(signupRequest.getLogin())) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different login");
//        }
//       Account account = new Account();
//        account.setLogin(signupRequest.getLogin());
//        account.setBalance(BigDecimal.ZERO);
//        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//        account.setCreateAccountDate(LocalDate.now());
//        account.setOwner(signupRequest.getLogin());
//        accountService.save(account);
//        return ResponseEntity.ok("Account created");
//    }

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

//    private Account getAccountFromAuthentication(Authentication authentication) {
//        return null;
//    }
//
//}