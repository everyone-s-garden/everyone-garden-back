package com.everyonegarden;

import com.everyonegarden.weather.api.WeatherApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class EveryoneGardenApplicationTests {

    @Test
    void contextLoads() throws IOException, ParseException {

        WeatherApi wa = new WeatherApi();

        String result = wa.getWeather("60","125");

    }

}
