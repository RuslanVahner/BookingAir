package com.vahner.airticketsapp.config;

import com.vahner.airticketsapp.service.impl.UserDetailServiceImpl;
import com.vahner.airticketsapp.validation.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

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
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        return new UserDetailServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/login",
                                "api/auth/signup",
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers(ADMIN_ONLY).hasAnyRole("ADMIN")
                        .requestMatchers(PASSENGER_ONLY).hasAnyRole("PASSENGER")
                        .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
