package com.everyonegarden.auth.service;

import com.everyonegarden.auth.client.ClientGoogle;
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
public class GoogleAuthService {

    private final ClientGoogle clientGoogle;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository userRepository;

    @Transactional
    public AuthResponse login(String accessToken) {
        Member googleMember = clientGoogle.getUserData(accessToken);
        String socialId = googleMember.getSocialId();
        Member member = userRepository.findBySocialId(socialId);

        AuthToken appToken = authTokenProvider.createUserAppToken(socialId, member.getId());

        if (member == null) {
            userRepository.save(googleMember);
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
