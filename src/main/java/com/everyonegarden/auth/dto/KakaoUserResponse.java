package com.everyonegarden.auth.dto;

import com.everyonegarden.member.entity.Name;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoUserResponse {

    private Long id;
    private KakaoAccount kakaoAccount;

    public KakaoUserResponse(Long id, KakaoAccount kakaoAccount) {
        this.id = id;
        this.kakaoAccount = kakaoAccount;
    }

    @Getter
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Properties {
        private Name name;

        public Properties(Name name) {
            this.name = name;
        }

    }

    @Getter
    @NoArgsConstructor
    public static class KakaoAccount {
        private String email;

        public KakaoAccount(String email) {
            this.email = email;
        }

    }

}
