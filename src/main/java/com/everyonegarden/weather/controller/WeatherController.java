package com.everyonegarden.weather.controller;

import com.everyonegarden.weather.dto.ApiWeatherDto;
import com.everyonegarden.weather.dto.ApiWeatherRandomResult;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.entity.Region;
import com.everyonegarden.weather.repository.RegionRepository;
import com.everyonegarden.weather.service.WeatherAllService;
import com.everyonegarden.weather.service.WeatherResponseService;
import com.everyonegarden.weather.service.WeatherFetchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private RegionRepository regionRepository;
    private final WeatherFetchService weatherFetchService;
    public final WeatherResponseService weatherResponseService;
    public final WeatherAllService weatherAllService;

    /*
     * 위치를 지정한 경우
     */
    @GetMapping("/weather")
    public ResponseEntity<ApiWeatherResult> weatherRegionRequest(@RequestParam("region") String regionName) {


        System.out.println(regionName);
        System.out.println(regionRepository.findByRegionName("서울특별시"));
        Region region = regionRepository.findByRegionName(regionName);

        List<ApiWeatherDto> list = null;

        try {

            list = weatherFetchService.getWeather(region.getNx(),region.getNy());

        }catch (Exception e){
            e.printStackTrace();
        }


        return new ResponseEntity<>(weatherResponseService.getWeatherResult(list), HttpStatus.OK);

    }

    /*
     * 위치를 지정하지 않은 경우
     */
    @GetMapping("/weather/all")
    public ResponseEntity<ApiWeatherRandomResult> weatherAllRequest() throws Exception {

        return weatherAllService.getRandomWeather();

    }


}
