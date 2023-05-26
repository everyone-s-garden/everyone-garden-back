package com.everyonegarden.weather.service.shortterm;

import com.everyonegarden.weather.dto.ApiWeatherShortDto;
import com.everyonegarden.weather.dto.ApiWeatherResult;
import com.everyonegarden.weather.repository.RegionRandomMapping;
import com.everyonegarden.weather.repository.RegionRepository;

import com.everyonegarden.weather.service.WeatherResponseService;
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

    private final WeatherShortApiService weatherShortService;
    public final WeatherResponseService weatherResponseService;


    public ResponseEntity<ApiWeatherResult> getAllRegionWeather() throws Exception {

        // 모든 지역에 대한 좌표
        List<RegionRandomMapping> allRegion = regionRepository.findAllBy();
        List<ApiWeatherShortDto> result = new ArrayList<>();


        // 현재 시간 구하기
        LocalTime localTime= LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH");
        String timeformat = localTime.format(formatterTime)+"00";

        // 현재 날짜 구하기
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dayformat = today.format(formatterDate);


        for(RegionRandomMapping region : allRegion){
            JsonArray jsonItemList = weatherShortService.shortWeather(region.getNx(),region.getNy());
            for(Object o : jsonItemList){
                JsonObject item = (JsonObject) o;
                if(checkNow(item,timeformat,dayformat))
                    result.add(new ApiWeatherShortDto(item,region.getRegionName()));
            }

        }

        return new ResponseEntity<>(weatherResponseService.getWeatherResult(result), HttpStatus.OK);

    }


    public boolean checkNow(JsonObject item,String time, String date) {

        String category = item.get("category").getAsString();
        String fcstTime = item.get("fcstTime").getAsString();
        String fcstDate = item.get("fcstDate").getAsString();

        if ((category.equals("POP") ||
                category.equals("SKY") ||
                category.equals("TMP") ) && fcstTime.equals(time) && fcstDate.equals(date)
        ) return true;

        return false;
    }



}
