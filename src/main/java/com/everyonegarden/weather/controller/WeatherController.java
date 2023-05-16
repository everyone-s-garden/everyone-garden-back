package com.everyonegarden.weather.controller;

import com.everyonegarden.weather.dto.ApiWeatherResponse;
import com.everyonegarden.weather.service.ApiWeatherResponseService;
import com.everyonegarden.weather.service.WeatherFetchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherFetchService weatherFetchService;
    public final ApiWeatherResponseService apiWeahterResponseService;

    @GetMapping("/weather")
    public ResponseEntity<ApiWeatherResponse> weatherRequest(@RequestParam("lng") String lng,
                                                             @RequestParam("lat") String lat) throws IOException, ParseException, URISyntaxException {


        return weatherFetchService.getWeather(lng,lat);

    }

}
