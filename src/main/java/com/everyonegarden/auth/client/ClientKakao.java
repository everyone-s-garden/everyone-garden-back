package com.everyonegarden.auth.client;

import com.everyonegarden.auth.dto.KakaoUserResponse;
import com.everyonegarden.auth.exception.TokenValidFailedException;
import com.everyonegarden.user.entity.User;
import com.everyonegarden.user.enunerate.UserProvider;
import org.springframework.http.HttpStatus;

public class ClientKakao implements ClientProxy{

    private final WebClient webClient;

    // TODO ADMIN 유저 생성 시 getAdminUserData 메소드 생성 필요
    @Override
    public User getUserData(String accessToken) {
        KakaoUserResponse kakaoUserResponse = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(KakaoUserResponse.class)
                .block();

        return User.builder()
                .socialId(String.valueOf(kakaoUserResponse.getId()))
                .name(kakaoUserResponse.getProperties().getNickname())
                .email(kakaoUserResponse.getKakaoAccount().getEmail())
                .userProvider(UserProvider.KAKAO)
                .roleType("ROLE_USER")
                .build();
    }
}
