package com.everyonegarden.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public MainResponse main() {
        return MainResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message("모두의 텃밭 개발용 TEST API입니다")
                .build();
    }
}
