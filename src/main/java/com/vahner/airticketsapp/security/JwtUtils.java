package com.vahner.airticketsapp.security;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public final class JwtUtils {
//    public static JwtAuthentication generate(Claims claims) {
//        final JwtAuthentication jwtTokenData = new JwtAuthentication();
//        jwtTokenData.setRoles(getRoles(claims));
//        jwtTokenData.setLogin(claims.getSubject());
//        return jwtTokenData;
//    }
//
//    private static Set<Role> getRoles(Claims claims) {
//        final List<String> roles = claims.get("role", List.class);
//        return roles.stream()
//                .map(Role::valueOf)
//                .collect(Collectors.toSet());
//    }
//}