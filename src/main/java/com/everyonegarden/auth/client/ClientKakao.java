package com.everyonegarden.auth.client;

import com.everyonegarden.auth.dto.KakaoUserResponse;
import com.everyonegarden.auth.exception.TokenValidFailedException;
import com.everyonegarden.global.error.ErrorCode;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.entity.RoleType;
import com.everyonegarden.member.enunerate.MemberProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ClientKakao implements ClientProxy{

    private final WebClient webClient;

    public ClientKakao(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Member getUserData(String accessToken) {

        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.set("Authorization",accessToken))
                .retrieve()
                .onStatus(status-> status.is4xxClientError(), response -> Mono.error(new TokenValidFailedException(ErrorCode.UNAUTHORIZED_TOKEN)))
                .onStatus(status-> status.is5xxServerError(), response -> Mono.error(new TokenValidFailedException(ErrorCode.OAUTH_CLIENT_SERVER_ERROR)))
                .bodyToMono(KakaoUserResponse.class)
                .block();

        return Member.builder()
                .socialId(String.valueOf(kakaoUserResponse.getId()))
                .memberProvider(MemberProvider.KAKAO)
                .roleType(RoleType.USER)
                .build();
    }

}
