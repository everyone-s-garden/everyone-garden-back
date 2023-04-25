package com.everyonegarden.auth.client;

import com.everyonegarden.user.entity.User;
import org.springframework.http.HttpStatus;

public class ClientGoogle implements ClientProxy{

    private final WebClient webClient;

    @Override
    public User getUserDate(String accessToken) {

        GoogleUserResponse googleUserResponse = webClient.get()
                .uri("https://oauth2.googleapis.com/tokeninfo", builder -> builder.queryParam("id_token", accessToken).build())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new TokenValidFailedException("Social Access Token is unauthorized")))
                .onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new TokenValidFailedException("Internal Server Error")))
                .bodyToMono(GoogleUserResponse.class)
                .block();

        return User.builder()
                .socialId(googleUserResponse.getSub())
                .name(googleUserResponse.getName())
                .email(googleUserResponse.getEmail())
                .memberProvider(UserProvider.GOOGLE)
                .roleType(RoleType.USER)
                .build();
    }
}
