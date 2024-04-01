package com.vahner.airticketsapp.validation;

import com.vahner.airticketsapp.generator.JwtProvider;
import com.vahner.airticketsapp.service.impl.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final UserDetailServiceImpl userDetailService;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, UserDetailServiceImpl userDetailService) {
        this.jwtProvider = jwtProvider;
        this.userDetailService = userDetailService;
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        if (request.getRequestURI().contains("/api/auth/login") ||
//                request.getRequestURI().contains("/api/auth/signup") ||
//                request.getRequestURI().contains("/swagger-ui/") ||
//                request.getRequestURI().contains("/v3/api-docs/")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//
//        final String token = getTokenFromRequest(request);
//        try {
//            if (token != null && jwtProvider.validateAccessToken(token)) {
//                final Claims claims = jwtProvider.getAccessClaims(token);
//                final JwtAuthentication jwtTokenData = JwtUtils.generate(claims);
//                jwtTokenData.setAuthenticated(true);
//                SecurityContextHolder.getContext().setAuthentication((Authentication) jwtTokenData);
//            }
//        } catch (JwtException ex) {
//            log.error("JWT validation failed: {}", ex.getMessage());
//            ((HttpServletResponse) servletResponse)
//                    .sendError(HttpServletResponse.SC_UNAUTHORIZED, ErrorMessage.M_INVALID_TOKEN);
//            return;
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

        @Override
        protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                        @NonNull FilterChain filterChain) {
            String jwt = getTokenFromRequest(request);
            if(jwt != null && jwtProvider.validateAccessToken(jwt)) {
                String username = jwtProvider.getClaims(jwt).getSubject(); //get username

                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                try {
                    filterChain.doFilter(request, response);
                } catch (IOException | ServletException e) {
                    throw new RuntimeException(e);
                }
            }

    }
}