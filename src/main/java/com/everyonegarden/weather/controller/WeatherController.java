package com.everyonegarden.weather.controller;

import com.everyonegarden.weather.dto.WeatherResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WeatherController {

    @GetMapping("/weather")
    public String weatherRequest(@RequestParam("x") String xCoordinate,
                                                            @RequestParam("y") String yCoordinate){




        return "";

    }

}
