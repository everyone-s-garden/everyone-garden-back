package com.everyonegarden.weather.controller;


import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.dto.ApiWeatherTimeDto;
import com.everyonegarden.weather.dto.WeatherDto;
import com.everyonegarden.weather.service.midterm.WeatherWeekService;
import com.everyonegarden.weather.service.shortterm.all.WeatherAllRegionService;
import com.everyonegarden.weather.service.WeatherResponseService;
import com.everyonegarden.weather.service.shortterm.time.WeatherOneRegionService;

import java.util.List;
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

    public final WeatherWeekService weatherWeekService ;

    /*
     * 위치를 지정한 경우 시간대별
     */
    @GetMapping("/time")
    public ResponseEntity<List<ApiWeatherTimeDto>> weatherRegionRequest( @RequestParam("lat") String lat,
                                                                  @RequestParam("long") String lng) throws Exception {
        return weatherOneRegionService.getRegionWeather(lat,lng);
    }

    /*
     * 위치를 지정한 경우 주간별
     */
    @GetMapping("/week")
    public ResponseEntity<List<WeatherDto>> weatherWeekRequest( @RequestParam("lat") String lat,
                                                                @RequestParam("long") String lng) throws Exception {
        return weatherWeekService.getWeekWeather(lat,lng);
    }

    /*
     * 위치를 지정하지 않은 경우
     */
    @GetMapping("/all")
    public ResponseEntity<ApiWeatherResult> weatherAllRequest() throws Exception {

        return weatherAllRegionService.getAllRegionWeather();

    }

}
