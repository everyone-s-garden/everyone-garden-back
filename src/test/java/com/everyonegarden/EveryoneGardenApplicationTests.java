package com.everyonegarden;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@ContextConfiguration(classes = EveryoneGardenApplication.class)
class EveryoneGardenApplicationTests {

    @Test
    void contextLoads() {

        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 포맷 적용
        String formatedNow = now.format(formatter);

        // 결과 출력
        System.out.println(formatedNow);

    }

}
