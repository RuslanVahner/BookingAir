package com.vahner.airticketsapp.config;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity()
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final JwtFilter jwtFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .sessionManagement(man -> man.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers(
//                                        "/api/auth/login",
//                                        "/api/auth/new-token",
//                                        "/api/auth/refresh"))
//                .formLogin(Customizer.withDefaults())
//                .logout(logoutPage -> logoutPage.logoutSuccessUrl("/"))
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//}