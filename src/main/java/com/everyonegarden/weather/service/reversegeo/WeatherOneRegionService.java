package com.everyonegarden.weather.service.reversegeo;

import com.everyonegarden.weather.dto.ApiWeatherDto;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.entity.Region;
import com.everyonegarden.weather.repository.RegionRepository;
import com.everyonegarden.weather.service.WeatherFetchService;
import com.everyonegarden.weather.service.WeatherResponseService;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherOneRegionService {
    private final RegionRepository regionRepository ;
    private final WeatherFetchService weatherFetchService;
    public final WeatherResponseService weatherResponseService;

    public final ReverseGeoFetchService reverseGeoFetchService;


    public ResponseEntity<ApiWeatherResult> getRegionWeather(String lat, String lng) throws Exception {


        String regionName = reverseGeoFetchService.getRegionName(lat,lng);

        Region region = regionRepository.findByRegionName(regionName);


        List<ApiWeatherDto> result = new ArrayList<>();

        JsonArray jsonItemList = weatherFetchService.fetchWeather(region.getNx(),region.getNy());
        for(Object o : jsonItemList){
            JsonObject item = (JsonObject) o;
            if(check(item))
                result.add(makeWeatherDto(item,regionName));

        }

        return new ResponseEntity<>(weatherResponseService.getWeatherResult(result), HttpStatus.OK);

    }

    public boolean check(JsonObject item) {

        String target = item.get("category").getAsString();

        if (target.equals("PCP") ||
                target.equals("SKY") ||
                target.equals("TMP")
        ) return true;


        return false;
    }
    private ApiWeatherDto makeWeatherDto(JsonObject item, String regionName) {

        ApiWeatherDto dto = ApiWeatherDto.builder()
                .baseDate(item.get("baseDate").getAsString())
                .baseTime(item.get("baseTime").getAsString())
                .category(item.get("category").getAsString())
                .fcstDate(item.get("fcstDate").getAsString())
                .fcstTime(item.get("fcstTime").getAsString())
                .fcstValue(item.get("fcstValue").getAsString())
                .nx(item.get("nx").getAsString())
                .ny(item.get("ny").getAsString())
                .regionName(regionName)
                .build();

        return dto;

    }
}

