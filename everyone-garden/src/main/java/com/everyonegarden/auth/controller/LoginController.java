package com.everyonegarden.auth.controller;

import com.everyonegarden.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController{

    private final UserRepository userRepository;

    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";


    }
    @GetMapping("/loginForm")
    public  String loginForm() {
        return "loginForm";
    }




}
