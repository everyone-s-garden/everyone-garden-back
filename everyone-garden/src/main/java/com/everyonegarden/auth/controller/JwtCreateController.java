package com.everyonegarden.auth.controller;


import com.everyonegarden.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

    private final UserRepository userRepository;
    private final BCrytPasswordEncoder bCrytPasswordEncoder;

    @PostMapping("/oauth/jwt/google")
    public String jwtCreate(@RequestBody Map<String, Object> data){
        System.out.println("jwtCreate 실행");
        System.out.println(data.get("profileObj"));


    }




}
