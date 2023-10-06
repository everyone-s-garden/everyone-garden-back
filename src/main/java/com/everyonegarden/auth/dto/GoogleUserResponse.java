package com.everyonegarden.auth.dto;

import com.everyonegarden.member.entity.Name;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleUserResponse {

    private String sub;
    private String email;
    private Name name;

    public GoogleUserResponse(String sub, String email, Name name) {
        this.sub = sub;
        this.email = email;
        this.name = name;
    }

}
