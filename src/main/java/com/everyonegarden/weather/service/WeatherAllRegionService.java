package com.everyonegarden.weather.service;

import com.everyonegarden.weather.dto.ApiWeatherDto;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.repository.RegionRandomMapping;
import com.everyonegarden.weather.repository.RegionRepository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherAllRegionService {

    private final RegionRepository regionRepository ;
    private final WeatherFetchService weatherFetchService;
    public final WeatherResponseService weatherResponseService;


    public ResponseEntity<ApiWeatherResult> getAllRegionWeather() throws Exception {

        // 모든 지역에 대한 좌표
        List<RegionRandomMapping> allRegion = regionRepository.findAllBy();
        List<ApiWeatherDto> result = new ArrayList<>();


        // 현재 시간 구하기
        LocalTime localTime= LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH");
        String timeformat = localTime.format(formatterTime)+"00";

        // 현재 날짜 구하기
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dayformat = today.format(formatterDate);


        for(RegionRandomMapping region : allRegion){
            JsonArray jsonItemList = weatherFetchService.fetchWeather(region.getNx(),region.getNy());
            for(Object o : jsonItemList){
                JsonObject item = (JsonObject) o;
                if(checkNow(item,timeformat,dayformat))
                    result.add(makeWeatherDto(item,region.getRegionName()));
            }

        }

        return new ResponseEntity<>(weatherResponseService.getWeatherResult(result), HttpStatus.OK);

    }


    public boolean checkNow(JsonObject item,String time, String date) {

        String category = item.get("category").getAsString();
        String fcstTime = item.get("fcstTime").getAsString();
        String fcstDate = item.get("fcstDate").getAsString();

        if ((category.equals("PCP") ||
                category.equals("SKY") ||
                category.equals("TMP") ) && fcstTime.equals(time) && fcstDate.equals(date)
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
