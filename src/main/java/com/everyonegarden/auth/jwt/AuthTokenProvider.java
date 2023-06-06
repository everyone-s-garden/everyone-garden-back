package com.everyonegarden.auth.jwt;

import com.everyonegarden.auth.CustomUser;
import com.everyonegarden.auth.exception.TokenValidFailedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthTokenProvider {

    @Value("${app.auth.tokenExpiry}")
    private String expiry;

    private final Key key;
    private static final String AUTHORITIES_KEY = "role";

    public AuthTokenProvider(@Value("${app.auth.tokenSecret}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public AuthToken createToken(String id, String role, String expiry,Long memberId) {
        Date expiryDate = getExpiryDate(expiry);
        return AuthToken.builder()
                .socialId(id)
                .memberId(memberId)

                .role(role)
                .expiry(expiryDate)

                .key(key)

                .build();
    }

    public AuthToken createUserAppToken(String id, Long memberId) {
        return createToken(id, "ROLE_USER", expiry,memberId);
    }

    public AuthToken convertAuthToken(String token) {
        return new AuthToken(token, key);
    }

    public static Date getExpiryDate(String expiry) {
        return new Date(System.currentTimeMillis() + Long.parseLong(expiry));
    }

    public Authentication getAuthentication(AuthToken authToken) {

        Claims claims = authToken.getTokenClaims();
        if (claims == null) {
            return null;
        }

        List<SimpleGrantedAuthority> authorities = Arrays.stream(new String[]{claims.get(AUTHORITIES_KEY).toString()})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        int memberId = (int) claims.get("memberId");

        CustomUser customUser = CustomUser.builder()
                .socialId(claims.getSubject())
                .memberId((long) memberId)
                .authorities(authorities)
                .build();

        return new UsernamePasswordAuthenticationToken(customUser, authToken, authorities);
    }

}
