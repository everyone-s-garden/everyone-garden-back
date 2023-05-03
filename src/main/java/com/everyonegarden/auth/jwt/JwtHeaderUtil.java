package com.everyonegarden.auth.jwt;

import javax.servlet.http.HttpServletRequest;

public class JwtHeaderUtil {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    public static String getAccessToken(HttpServletRequest request) {

        //header Authorization에 있는 String
        String headerValue = request.getHeader(HEADER_AUTHORIZATION);

        if (headerValue == null) {
            return null;
        }

        // String "Bearer"로 시작한다면
        if (headerValue.startsWith(TOKEN_PREFIX)) {
            // 그 이후부터 return
            return headerValue.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
