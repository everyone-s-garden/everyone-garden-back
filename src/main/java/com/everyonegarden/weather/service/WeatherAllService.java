package com.everyonegarden.weather.service;

import com.everyonegarden.weather.dto.ApiWeatherDto;
import com.everyonegarden.weather.dto.ApiWeatherRandomResult;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.entity.Region;
import com.everyonegarden.weather.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherAllService {

    private final RegionRepository regionRepository ;
    private final WeatherFetchService weatherFetchService;
    public final WeatherResponseService weatherResponseService;

    //weatherFetchService.getWeather(lat,lng);
    public ResponseEntity<ApiWeatherRandomResult> getRandomWeather() throws Exception {

        Random random = new Random();
        Long id = (long)random.nextInt(17)+1;//1~17

        Optional<Region> randomRegion = regionRepository.findById(id);

        // y위도, x경도
        List<ApiWeatherDto> list = weatherFetchService.getWeather(randomRegion.get().getNx(), randomRegion.get().getNy());


        return new ResponseEntity<>(weatherResponseService.getWeatherRandomResult(list,randomRegion.get().getRegionName()), HttpStatus.OK);
    }

}
