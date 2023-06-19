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

import java.util.Optional;

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
        Optional<Member> memberOptional = userRepository.findBySocialIdOptional(socialId);
        boolean tag = false;
        AuthToken appToken;

        if (memberOptional.isEmpty()) {
            Member savedMember = userRepository.save(googleMember);
            appToken = authTokenProvider.createUserAppToken(socialId, savedMember.getId());
            tag=true;
        } else {
            appToken = authTokenProvider.createUserAppToken(socialId, memberOptional.get().getId());
        }

        return AuthResponse.builder()
                .appToken(appToken.getToken())
                .isNewMember(tag)
                .userId(userRepository.findBySocialId(socialId).getId())
                .name(userRepository.findBySocialId(socialId).getName())
                .build();

    }
}