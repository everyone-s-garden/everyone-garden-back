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
        Member member = userRepository.findBySocialId(socialId);


        AuthToken appToken = authTokenProvider.createUserAppToken(socialId);

        if (member == null) {
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
