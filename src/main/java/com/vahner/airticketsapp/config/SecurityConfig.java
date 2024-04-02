package com.vahner.airticketsapp.config;

import com.vahner.airticketsapp.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailService;

    private static final String[] ADMIN_ONLY = {
            "/api/account/get/{id}",
            "/api/account",
            "/api/account/createAccount",
            "/api/account/deleteAccount/{id}",
            "/api/account/updateAccount/{id}",
            "/api/account/getAccountBalance/{id}",
            "/api/auth/new-token",
            "/api/auth/refresh",
            "/api/flight/search",
            "/api/passenger",
            "/api/passenger/{id}",
            "/api/passenger/delete/{id}",
            "/api/passenger/update/{id}",
            "/api/passenger/create",
            "/api/tickets",
            "/api/tickets/create",
            "/api/tickets/delete/{id}",
            "/api/tickets/update/{id}",
    };

    private static final String[] PASSENGER_ONLY = {
            "/api/account/createAccount",
            "/api/account/deleteAccount/{id}",
            "/api/account/updateAccount/",
            "/api/auth/login",
            "/api/flight/search",
            "/api/passenger/create",
            "/api/tickets",
            "/api/tickets/create",
            "/api/tickets/delete/{id}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/api/auth/login",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**")
//                        .permitAll()
//                        .requestMatchers(ADMIN_ONLY).hasRole("ADMIN")
                        .requestMatchers(PASSENGER_ONLY).hasRole("PASSENGER")
                        .anyRequest().permitAll())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
