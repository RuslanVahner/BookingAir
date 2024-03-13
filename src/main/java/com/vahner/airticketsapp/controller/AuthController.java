package com.vahner.airticketsapp.controller;

//@RestController
//@RequestMapping("api/auth")
//@AllArgsConstructor
//public class AuthController {
//
//    private final AuthService service;
//
//    @PostMapping("login")
//    public JwtResponse authorisation(@RequestBody JwtRequest jwtRequest) {
//        return service.login(jwtRequest);
//    }
//
//    @PostMapping("new-token")
//    public JwtResponse getNewAccessToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
//        return service.getAccessToken(refreshJwtRequest.getRefreshToken());
//    }
//
//    @PostMapping("refresh")
//    public JwtResponse refreshJwtTokens(@RequestBody RefreshJwtRequest refreshJwtRequest) {
//        return service.refresh(refreshJwtRequest.getRefreshToken());
//    }
//}