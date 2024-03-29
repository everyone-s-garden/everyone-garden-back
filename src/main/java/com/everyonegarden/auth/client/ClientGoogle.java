package com.everyonegarden.auth.client;

import com.everyonegarden.auth.dto.GoogleUserResponse;
import com.everyonegarden.auth.exception.TokenValidFailedException;
import com.everyonegarden.global.error.ErrorCode;
import com.everyonegarden.member.entity.Member;
import com.everyonegarden.member.entity.RoleType;
import com.everyonegarden.member.enunerate.MemberProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ClientGoogle implements ClientProxy{

    private final WebClient webClient;

    public ClientGoogle(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Member getUserData(String accessToken) {

        GoogleUserResponse googleUserResponse = webClient.get()
                .uri("https://www.googleapis.com/oauth2/v3/userinfo")
                .headers(h -> h.set("Authorization",accessToken))
                .retrieve()
                .onStatus(status-> status.is4xxClientError(), response -> Mono.error(new TokenValidFailedException(ErrorCode.UNAUTHORIZED_TOKEN)))
                .onStatus(status-> status.is5xxServerError(), response -> Mono.error(new TokenValidFailedException(ErrorCode.OAUTH_CLIENT_SERVER_ERROR)))
                .bodyToMono(GoogleUserResponse.class)
                .block();

        System.out.println("googleUserResponse:"+googleUserResponse);

        return Member.builder()
                .socialId(googleUserResponse.getSub())
                .memberProvider(MemberProvider.GOOGLE)
                .roleType(RoleType.USER)
                .build();
    }
}
