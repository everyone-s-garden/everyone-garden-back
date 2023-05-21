package com.everyonegarden.auth.service;


import com.everyonegarden.auth.client.ClientKakao;
import com.everyonegarden.auth.dto.AuthResponse;
import com.everyonegarden.auth.jwt.AuthToken;
import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final ClientKakao clientKakao;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository userRepository;

    @Transactional
    public AuthResponse login(String accessToken) {
        Member kakaoMember = clientKakao.getUserData(accessToken);
        String socialId = kakaoMember.getSocialId();
        Optional<Member> memberOptional = userRepository.findBySocialIdOptional(socialId);

        AuthToken appToken;

        if (memberOptional.isEmpty()) {
            Member savedMember = userRepository.save(kakaoMember);
            appToken = authTokenProvider.createUserAppToken(socialId, savedMember.getId());
        } else {
            appToken = authTokenProvider.createUserAppToken(socialId, memberOptional.get().getId());
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewMember(Boolean.FALSE)
                .build();

    }
}
