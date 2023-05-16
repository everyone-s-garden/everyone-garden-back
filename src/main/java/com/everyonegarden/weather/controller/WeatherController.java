package com.everyonegarden.weather.controller;

import com.everyonegarden.weather.dto.ApiWeatherDto;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.service.ApiWeatherResponseService;
import com.everyonegarden.weather.service.WeatherFetchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherFetchService weatherFetchService;
    public final ApiWeatherResponseService apiWeatherResponseService;

    @GetMapping("/weather")
    public ResponseEntity<ApiWeatherResult> weatherRequest(@RequestParam("lng") String lng,
                                                           @RequestParam("lat") String lat) {

        List<ApiWeatherDto> list = null;

        try {

            list = weatherFetchService.getWeather(lng,lat);

        }catch (Exception e){
            e.printStackTrace();
        }


        return new ResponseEntity<>(apiWeatherResponseService.getWeatherResult(list), HttpStatus.OK);

    }

}
