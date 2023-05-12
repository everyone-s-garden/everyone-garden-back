package com.everyonegarden.auth.client;


import com.everyonegarden.auth.dto.KakaoUserResponse;
import com.everyonegarden.auth.exception.TokenValidFailedException;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.enunerate.MemberProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientKakao implements ClientProxy{

    private final WebClient webClient;

    @Override
    public Member getUserData(String accessToken) {

        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponse.class)
                .block();

        return Member.builder()
                .socialId(String.valueOf(kakaoUserResponse.getId()))
                .name(kakaoUserResponse.getProperties().getNickname())
                .email(kakaoUserResponse.getKakaoAccount().getEmail())
                .memberProvider(MemberProvider.KAKAO)
                .roleType("ROLE_USER")
                .build();
    }
}
