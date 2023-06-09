package com.everyonegarden.auth.controller;

import com.everyonegarden.auth.dto.AuthResponse;
import com.everyonegarden.auth.jwt.AuthToken;
import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.auth.jwt.JwtHeaderUtil;
import com.everyonegarden.auth.service.AuthService;
import com.everyonegarden.auth.service.GoogleAuthService;
import com.everyonegarden.auth.service.KakaoAuthService;
import com.everyonegarden.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KakaoAuthService kakaoAuthService;
    private final GoogleAuthService googleAuthService;
    private final AuthTokenProvider authTokenProvider;
    private final AuthService authService;

    /**
     * KAKAO 소셜 로그인 기능
     * @return ResponseEntity<AuthResponse>
     */
    //@ApiOperation(value = "카카오 로그인", notes = "카카오 엑세스 토큰을 이용하여 사용자 정보 받아 저장하고 앱의 토큰 반환")
    @GetMapping(value = "/kakao")
    public ResponseEntity<AuthResponse> kakaoAuthRequest(HttpServletRequest request) {
        return ApiResponse.success(kakaoAuthService.login(request.getHeader("Authorization")));
    }

    /**
     * GOOGLE 소셜 로그인 기능
     * @return ResponseEntity<AuthResponse>
     */
    //@ApiOperation(value = "구글 로그인", notes = "구글 엑세스 토큰을 이용하여 사용자 정보 받아 저장하고 앱의 토큰 반환")
    @GetMapping(value = "/google")
    public ResponseEntity<AuthResponse> googleAuthRequest(HttpServletRequest request) {
        return ApiResponse.success(googleAuthService.login(request.getHeader("Authorization")));
    }

    /**
     * appToken 갱신
     * @return ResponseEntity<AuthResponse>
     */
    //@ApiOperation(value = "appToken 갱신", notes = "appToken 갱신")
    @GetMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken (HttpServletRequest request) {

        // appToken 분리
        String appToken = JwtHeaderUtil.getAccessToken(request);
        // 이 appToken으로
        AuthToken authToken = authTokenProvider.convertAuthToken(appToken);
        if (!authToken.isValid()) { // 형식에 맞지 않는 token
            return ApiResponse.forbidden(null);
        }

        AuthResponse authResponse = authService.updateToken(authToken);
        if (authResponse == null) { // token 만료
            return ApiResponse.forbidden(null);
        }
        return ApiResponse.success(authResponse);
    }
}
