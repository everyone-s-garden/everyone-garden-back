package com.everyonegarden.weather.service.shortterm;

import com.everyonegarden.weather.dto.ApiWeatherShortDto;
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

@Service
@RequiredArgsConstructor
public class WeatherOneRegionService {
    private final RegionRepository regionRepository ;
    private final WeatherShortApiService weatherShortService;
    public final WeatherResponseService weatherResponseService;

    public final ReverseGeoFetchService reverseGeoFetchService;


    public ResponseEntity<ApiWeatherResult> getRegionWeather(String lat, String lng) throws Exception {


        String regionName = reverseGeoFetchService.getRegionName(lat,lng);

        Region region = regionRepository.findByRegionName(regionName);


        List<ApiWeatherShortDto> result = new ArrayList<>();

        JsonArray jsonItemList = weatherShortService.shortWeather(region.getNx(),region.getNy());
        for(Object o : jsonItemList){
            JsonObject item = (JsonObject) o;
            if(check(item))
                result.add(new ApiWeatherShortDto(item,regionName));

        }

        return new ResponseEntity<>(weatherResponseService.getWeatherResult(result), HttpStatus.OK);

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

