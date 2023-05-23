package com.everyonegarden.weather.controller;


import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.service.WeatherAllRegionService;
import com.everyonegarden.weather.service.WeatherResponseService;
import com.everyonegarden.weather.service.reversegeo.WeatherOneRegionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/weather")
public class WeatherController {

    public final WeatherResponseService weatherResponseService;
    public final WeatherAllRegionService weatherAllRegionService;
    public final WeatherOneRegionService weatherOneRegionService;

    /*
     * 위치를 지정한 경우
     */
    @GetMapping("/time")
    public ResponseEntity<ApiWeatherResult> weatherRegionRequest( @RequestParam("lat") String lat,
                                                                  @RequestParam("long") String lng) throws Exception {
        return weatherOneRegionService.getRegionWeather(lat,lng);

    }

    /*
     * 위치를 지정하지 않은 경우
     */
    @GetMapping("/all")
    public ResponseEntity<ApiWeatherResult> weatherAllRequest() throws Exception {

        return weatherAllRegionService.getAllRegionWeather();

    }


}
