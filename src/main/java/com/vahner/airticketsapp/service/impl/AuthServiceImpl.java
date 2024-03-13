package com.vahner.airticketsapp.service.impl;

//@Service
//@RequiredArgsConstructor
//public class AuthServiceImpl implements AuthService {
//
//    private final AccountService accountService;
//    private final RefreshTokenRepository refreshTokenRepository;
//    private final JwtProvider jwtUtils;
//
//    @Override
//    public JwtResponse login(@NonNull JwtRequest authRequest) {
//        final Account account = accountService.getByLogin(authRequest.getLogin())
//                .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
//        if (account.getPassword().equals(authRequest.getPassword())) {
//            final String refreshToken = jwtUtils.generateRefreshToken(account);
//            refreshTokenRepository.save(account.getLogin(), refreshToken);
//            return new JwtResponse(jwtUtils.generateAccessToken(account), refreshToken);
//        } else {
//            throw new AuthException(ErrorMessage.M_WRONG_CREDENTIALS);
//        }
//    }
//
//    @Override
//    public JwtResponse getAccessToken(@NonNull String refreshToken) {
//        if (jwtUtils.validateRefreshToken(refreshToken)) {
//            final Claims claims = jwtUtils.getRefreshClaims(refreshToken);
//            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
//            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
//                final Account account = accountService.getByLogin(claims.getSubject())
//                        .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
//                return new JwtResponse(jwtUtils.generateAccessToken(account), null);
//            }
//        }
//        return new JwtResponse(null, null);
//    }
//
//    @Override
//    public JwtResponse refresh(@NonNull String refreshToken) {
//        if (jwtUtils.validateRefreshToken(refreshToken)) {
//            final Claims claims = jwtUtils.getRefreshClaims(refreshToken);
//            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
//            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
//                final Account account = accountService.getByLogin(claims.getSubject())
//                        .orElseThrow(() -> new AuthException(ErrorMessage.M_ACCOUNT_NOT_FOUND));
//                final String newRefreshToken = jwtUtils.generateRefreshToken(account);
//                refreshTokenRepository.save(account.getLogin(), newRefreshToken);
//                return new JwtResponse(jwtUtils.generateAccessToken(account), newRefreshToken);
//            }
//        }
//        throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
//    }
//
//    @Override
//    public JwtAuthentication getAuthInfo() {
//        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
//    }
//}