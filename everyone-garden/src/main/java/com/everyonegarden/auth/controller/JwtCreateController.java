package com.everyonegarden.auth.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.everyonegarden.auth.jwt.JwtProperties;
import com.everyonegarden.auth.oauth.NaverUser;
import com.everyonegarden.auth.oauth.OAuthUserInfo;
import com.everyonegarden.user.entity.User;
import com.everyonegarden.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCrytPasswordEncoder;

    @PostMapping("/oauth/jwt/naver")
    public String jwtNaverCreate(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate 실행");
        System.out.println(data.get("profileObj"));

        OAuthUserInfo naverUser = new NaverUser((Map<String,Object>)data.get("profileObj"));

        User userEntity = userRepository.findByUsername(naverUser.getProvider()+"_"+naverUser.getProviderId());

        if(userEntity==null){
            User userRequest = User.builder()
                    .username(naverUser.getProvider()+"_"+naverUser.getProviderId())
                    .password(bCrytPasswordEncoder.encode(JwtProperties.SECRET))
                    .email(naverUser.getEmail())
                    .userProvider(naverUser.getProvider())
                    .providerId(naverUser.getProviderId())
                    .roles("ROLE_USER")
                    .build();


            userEntity = userRepository.save(userRequest);


        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id",userEntity.getId())
                .withClaim("username",userEntity.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }

    @PostMapping("/oauth/jwt/kakao")
    public String jwtKakaoCreate(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate 실행");
        System.out.println(data.get("profileObj"));

        OAuthUserInfo kakaoUser = new NaverUser((Map<String,Object>)data.get("profileObj"));

        User userEntity = userRepository.findByUsername(kakaoUser.getProvider()+"_"+kakaoUser.getProviderId());

        if(userEntity==null){
            User userRequest = User.builder()
                    .username(kakaoUser.getProvider()+"_"+kakaoUser.getProviderId())
                    .password(bCrytPasswordEncoder.encode(JwtProperties.SECRET))
                    .email(kakaoUser.getEmail())
                    .userProvider(kakaoUser.getProvider())
                    .providerId(kakaoUser.getProviderId())
                    .roles("ROLE_USER")
                    .build();


            userEntity = userRepository.save(userRequest);


        }

        String jwtToken = JWT.create()
                .withSubject(userEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id",userEntity.getId())
                .withClaim("username",userEntity.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        return jwtToken;
    }



}
