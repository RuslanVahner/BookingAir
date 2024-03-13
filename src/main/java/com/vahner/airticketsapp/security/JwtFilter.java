package com.vahner.airticketsapp.security;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends GenericFilterBean {
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private final JwtProvider jwtProvider;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//        final String token = getTokenFromRequest((HttpServletRequest) servletRequest);
//        if (token != null && jwtProvider.validateAccessToken(token)) {
//            final Claims claims = jwtProvider.getAccessClaims(token);
//            final JwtAuthentication jwtTokenData = JwtUtils.generate(claims);
//            jwtTokenData.setAuthenticated(true);
//            SecurityContextHolder.getContext().setAuthentication(jwtTokenData);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        final String bearer = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
//            return bearer.substring(7);
//        }
//        return null;
//    }
//}