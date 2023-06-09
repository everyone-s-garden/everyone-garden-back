package com.everyonegarden.auth.jwt;

import io.jsonwebtoken.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class AuthToken {

    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    @Builder
    AuthToken(String socialId, String role,Long memberId, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(socialId, role, memberId, expiry);
    }

    private String createAuthToken(String socialId, String role,Long id, Date expiry) {
        return Jwts.builder()
                .setSubject(socialId)

                .claim(AUTHORITIES_KEY, role)
                .claim("memberId",id)

                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }

    public boolean isValid() {
        return this.getTokenClaims() != null;
    }

    public Claims getTokenClaims() {
        Claims claims = null;

        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            claims = jwtParser
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        } catch (Exception e) {
            return null;
        }

        return claims;
    }
}
