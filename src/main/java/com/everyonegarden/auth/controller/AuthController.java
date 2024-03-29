package com.everyonegarden.auth.controller;

import com.everyonegarden.auth.dto.AuthResponse;
import com.everyonegarden.auth.jwt.AuthToken;
import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.auth.jwt.JwtHeaderUtil;
import com.everyonegarden.auth.service.AuthService;
import com.everyonegarden.auth.service.ClientService;
import com.everyonegarden.member.enunerate.MemberProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthTokenProvider authTokenProvider;
    private final ClientService clientService;
    private final AuthService authService;


    public AuthController(AuthTokenProvider authTokenProvider, ClientService clientService, AuthService authService) {
        this.authTokenProvider = authTokenProvider;
        this.clientService = clientService;
        this.authService = authService;
    }

    @GetMapping(value = "/kakao")
    public ResponseEntity<AuthResponse> kakaoAuthRequest(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.login(MemberProvider.KAKAO.name(), accessToken));
    }

    @GetMapping(value = "/google")
    public ResponseEntity<AuthResponse> googleAuthRequest(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientService.login(MemberProvider.GOOGLE.name(), accessToken));
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken( HttpServletRequest request) {

        String appToken = JwtHeaderUtil.getAccessToken(request);
        AuthToken authToken = authTokenProvider.convertAuthToken(appToken);

        if (!authToken.isValidTokenClaims()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        AuthResponse authResponse = authService.updateToken(authToken);
        if (authResponse == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(authResponse);
    }

}
