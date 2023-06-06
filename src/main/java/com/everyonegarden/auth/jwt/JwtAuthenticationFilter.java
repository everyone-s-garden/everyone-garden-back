package com.everyonegarden.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String AUTHORIZATION_HEADER = request.getHeader("Authorization");
        if (AUTHORIZATION_HEADER == null || !AUTHORIZATION_HEADER.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String TOKEN_WITHOUT_PREFIX = AUTHORIZATION_HEADER.replace("Bearer ", "");
        AuthToken token = tokenProvider.convertAuthToken(TOKEN_WITHOUT_PREFIX);
        Authentication authentication = tokenProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

}
