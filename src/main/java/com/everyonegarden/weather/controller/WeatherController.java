package com.everyonegarden.weather.controller;

import com.everyonegarden.weather.infra.dto.WeatherApiResult;
import com.everyonegarden.weather.infra.dto.WeatherTimeApiResponse;
import com.everyonegarden.weather.infra.dto.WeatherMidResponse;
import com.everyonegarden.weather.facade.WeatherFacade;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/weather")
public class WeatherController {
    
    private final WeatherFacade weatherFacade;

    public WeatherController(WeatherFacade weatherFacade) {
        this.weatherFacade = weatherFacade;
    }

    /*
     ** 위치를 지정한 경우 시간대별
     */
    @GetMapping(
            value="/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WeatherTimeApiResponse>> weatherRegionRequest(@RequestParam("lat") String lat,
                                                                             @RequestParam("long") String lng) {
        return ResponseEntity.ok(weatherFacade.getRegionWeather(lng, lat));
    }

    /*
     ** 위치를 지정한 경우 주간별
     */
    @GetMapping(
            value = "/week",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WeatherMidResponse>> weatherWeekRequest(@RequestParam("lat") String lat,
                                                                       @RequestParam("long") String lng) {
        return ResponseEntity.ok(weatherFacade.getWeekWeather(lng, lat));
    }

    /*
     ** 위치를 지정하지 않은 경우
     */
    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WeatherApiResult> weatherAllRequest() {

        return ResponseEntity.ok(weatherFacade.getAllRegionWeather());
    }

}
