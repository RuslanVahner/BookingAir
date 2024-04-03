package com.vahner.airticketsapp.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;


    private static final String[] PUBLIC_MATCHERS = {
            "/api/users/register",
            "/api/users/login",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private static final String[] USER_POST_MATCHERS = {
            "/api/bookings/createBooking",
            "/api/reviews/createReview",
            "/api/flight/createFlight"
    };

    private static final String[] USER_GET_MATCHERS = {
            "/api/bookings/**",
            "/api/reviews/**",
            "/api/flight/**",
            "/api/users/**"

    };

    private static final String[] ADMIN_MATCHERS = {
            "/api/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PUBLIC_MATCHERS).permitAll()
                        .requestMatchers(HttpMethod.POST, USER_POST_MATCHERS).hasAnyRole("USER")
                        .requestMatchers(HttpMethod.GET, USER_GET_MATCHERS).hasAnyRole("USER")
                        .requestMatchers(ADMIN_MATCHERS).hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(fromLogin -> fromLogin
                        .defaultSuccessUrl("/swagger-ui/index.html", true))
                .httpBasic(Customizer.withDefaults());
        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}