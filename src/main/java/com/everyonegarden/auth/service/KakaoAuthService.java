package com.everyonegarden.auth.service;

import com.everyonegarden.auth.client.ClientKakao;
import com.everyonegarden.auth.dto.AuthRequest;
import com.everyonegarden.auth.dto.AuthResponse;
import com.everyonegarden.auth.jwt.AuthToken;
import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.user.entity.User;
import com.everyonegarden.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final ClientKakao clientKakao;
    private final AuthTokenProvider authTokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        User kakaoMember = clientKakao.getUserData(authRequest.getAccessToken());
        String socialId = kakaoMember.getSocialId();
        User user = userRepository.findBySocialId(socialId);

        AuthToken appToken = authTokenProvider.createUserAppToken(socialId);

        if (user == null) {
            userRepository.save(kakaoMember);
            return AuthResponse.builder()
                    .appToken(appToken.getToken())
                    .isNewMember(Boolean.TRUE)
                    .build();
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewMember(Boolean.FALSE)
                .build();
    }
}
