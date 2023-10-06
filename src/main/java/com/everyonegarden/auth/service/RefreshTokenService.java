package com.everyonegarden.auth.service;

import com.everyonegarden.auth.jwt.AuthToken;
import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.auth.jwt.JwtTokenCache;
import com.everyonegarden.auth.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RefreshTokenService {

    private final AuthTokenProvider authTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(AuthTokenProvider authTokenProvider, RefreshTokenRepository refreshTokenRepository) {
        this.authTokenProvider = authTokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public AuthToken saveNewAccessTokenInfo(Long memberId, String socialId, String accessToken) {
        AuthToken refreshToken = findRefreshToken(accessToken);
        AuthToken newAccessToken = authTokenProvider.createAccessToken(socialId,memberId);

        refreshTokenRepository.save(new JwtTokenCache(memberId, refreshToken.getToken(), newAccessToken.getToken()));

        return newAccessToken;
    }

    public AuthToken saveAccessTokenCache(Long memberId, String socialId) {
        AuthToken newAccessToken = authTokenProvider.createAccessToken(socialId,memberId);
        AuthToken newRefreshToken = authTokenProvider.createRefreshToken();

        refreshTokenRepository.save(new JwtTokenCache(memberId, newRefreshToken.getToken(), newAccessToken.getToken()));

        return newAccessToken;
    }

    public boolean isExpiredRefreshToken (String accessToken) {
        return findRefreshToken(accessToken).isValidTokenClaims();
    }

    @Transactional(readOnly=true)
    public AuthToken findRefreshToken (String accessToken) {
        JwtTokenCache jwtTokenCache = refreshTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new RuntimeException()); // to do : 예외 고치기
        return authTokenProvider.convertAuthToken(jwtTokenCache.getRefreshToken());
    }

}
