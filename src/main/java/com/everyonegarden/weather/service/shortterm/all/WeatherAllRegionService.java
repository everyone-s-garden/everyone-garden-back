package com.everyonegarden.weather.service.shortterm.all;

import com.everyonegarden.weather.dto.ApiWeatherAllDto;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.dto.ApiWeatherTimeDto;
import com.everyonegarden.weather.repository.RegionRandomMapping;
import com.everyonegarden.weather.repository.RegionRepository;

import com.everyonegarden.weather.service.WeatherResponseService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherAllRegionService {

    private final RegionRepository regionRepository ;

    private final WeatherAllApiService weatherAllService;
    public final WeatherResponseService weatherResponseService;


    public ResponseEntity<ApiWeatherResult> getAllRegionWeather() throws Exception {



        // 모든 지역에 대한 좌표
        List<RegionRandomMapping> allRegion = regionRepository.findAllBy();
        List<ApiWeatherAllDto> result = new ArrayList<>();


        for(RegionRandomMapping region : allRegion){
            JsonArray jsonItemList = weatherAllService.allWeather(region.getNx(),region.getNy());
            for(Object o : jsonItemList){
                JsonObject item = (JsonObject) o;
                if(checkNow(item))
                    result.add(new ApiWeatherAllDto(item,region.getRegionName()));
            }

        }
        Map<String, List<ApiWeatherAllDto>> groupedData = result.stream().collect(Collectors.groupingBy(ApiWeatherAllDto::getRegionName));

        return new ResponseEntity<>(weatherResponseService.getWeatherAllResult(groupedData), HttpStatus.OK);

    }


    public boolean checkNow(JsonObject item) {

        String category = item.get("category").getAsString();

        if (category.equals("T1H") ||
                category.equals("PTY")) return true;

        return false;
    }



}
