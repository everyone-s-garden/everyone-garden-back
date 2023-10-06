package com.everyonegarden.auth.service;

import com.everyonegarden.auth.client.ClientProxy;
import com.everyonegarden.auth.client.ClientStrategy;
import com.everyonegarden.auth.dto.AuthResponse;
import com.everyonegarden.auth.jwt.AuthToken;
import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientStrategy clientStrategy;
    private final AuthTokenProvider authTokenProvider;
    private final MemberRepository memberJpaRepository;
    private final RefreshTokenService refreshTokenService;

    public ClientService(ClientStrategy clientStrategy, AuthTokenProvider authTokenProvider, MemberRepository memberJpaRepository, RefreshTokenService refreshTokenService) {
        this.clientStrategy = clientStrategy;
        this.authTokenProvider = authTokenProvider;
        this.memberJpaRepository = memberJpaRepository;
        this.refreshTokenService = refreshTokenService;
    }

    @Transactional
    public AuthResponse login(String client, String accessToken) {
        ClientProxy clientProxy = clientStrategy.getClient(client);

        Member clientMember = clientProxy.getUserData(accessToken);
        String socialId = clientMember.getSocialId();

        Optional<Member> memberOptional = memberJpaRepository.findMemberIfExisted(socialId);
        Member savedMember = memberOptional.orElseGet(() -> memberJpaRepository.save(clientMember));
        
        AuthToken newAuthToken = refreshTokenService.saveAccessTokenCache(savedMember.getId(),socialId);

        return AuthResponse.builder()
                .appToken(newAuthToken.getToken())
                .isNewMember(!memberOptional.isPresent())
                .userId(savedMember.getId())
                .name(savedMember.getName())
                .build();
    }

}
