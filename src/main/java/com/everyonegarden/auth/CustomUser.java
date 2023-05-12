package com.everyonegarden.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
@AllArgsConstructor @Builder
public class CustomUser {

    private final Long memberId;
    private final String socialId;
    private final List<? extends GrantedAuthority> authorities;

}