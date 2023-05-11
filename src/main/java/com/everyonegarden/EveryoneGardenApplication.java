package com.everyonegarden;

import com.everyonegarden.weather.api.WeatherApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class EveryoneGardenApplication {

    public static void main(String[] args) {
        SpringApplication.run(EveryoneGardenApplication.class, args);
        
    }

}
