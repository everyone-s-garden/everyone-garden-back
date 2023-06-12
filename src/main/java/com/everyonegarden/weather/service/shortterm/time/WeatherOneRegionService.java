package com.everyonegarden.weather.service.shortterm.time;

import com.everyonegarden.weather.dto.ApiWeatherMidAmDto;
import com.everyonegarden.weather.dto.ApiWeatherMidPmDto;
import com.everyonegarden.weather.dto.ApiWeatherTimeDto;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.entity.Region;
import com.everyonegarden.weather.repository.RegionRepository;
import com.everyonegarden.weather.service.WeatherResponseService;

import com.everyonegarden.weather.service.shortterm.reversegeo.ReverseGeoFetchService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherOneRegionService {
    private final RegionRepository regionRepository ;
    private final WeatherTimeApiService weatherShortService;
    public final WeatherResponseService weatherResponseService;

    public final ReverseGeoFetchService reverseGeoFetchService;


    public ResponseEntity<ApiWeatherResult> getRegionWeather(String lat, String lng) throws Exception {


        String regionName = reverseGeoFetchService.getRegionName(lat,lng);

        Region region = regionRepository.findByRegionName(regionName);


        List<ApiWeatherTimeDto> result = new ArrayList<>();

        JsonArray jsonItemList = weatherShortService.shortWeather(region.getNx(),region.getNy());
        for(Object o : jsonItemList){
            JsonObject item = (JsonObject) o;
            if(check(item))
                result.add(new ApiWeatherTimeDto(item,regionName));

        }
        Map<String, List<ApiWeatherTimeDto>> groupedData = result.stream()
                .collect(Collectors.groupingBy(ApiWeatherTimeDto::getRegionName));

        return new ResponseEntity<>(weatherResponseService.getWeatherTimeResult(groupedData), HttpStatus.OK);

    }

    public boolean check(JsonObject item) {

        String target = item.get("category").getAsString();

        if (target.equals("POP") ||
                target.equals("SKY") ||
                target.equals("TMP")
        ) return true;


        return false;
    }

}

