package com.everyonegarden.weather.service.midterm;

import com.everyonegarden.weather.dto.ApiWeatherMidAmDto;
import com.everyonegarden.weather.dto.ApiWeatherMidPmDto;

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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherWeekService {
    private final RegionRepository regionRepository ;

    public final WeatherResponseService weatherResponseService;

    public final ReverseGeoFetchService reverseGeoFetchService;
    public final WeatherMidApiService weatherMidApiService;

    public final GetOneTwoDayService getOneTwoDayService;

    public ResponseEntity<ApiWeatherResult> getWeekWeather(String lat, String lng) throws Exception {


        String regionName = reverseGeoFetchService.getRegionName(lat, lng);

        Region region = regionRepository.findByRegionName(regionName);

        ArrayList<String> skyOneTwo = getOneTwoDayService.getSkyOneTwo(region);

        // 현재 시간 구하기
        LocalTime localTime= LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH");
        int timeformat = Integer.parseInt(localTime.format(formatterTime));

        if(timeformat<=12) {
            List<ApiWeatherMidPmDto> result = new ArrayList<>();
            JsonArray jsonItemList = weatherMidApiService.midWeather(region.getRegid());
            for (Object o : jsonItemList) {
                JsonObject item = (JsonObject) o;
                result.add(new ApiWeatherMidPmDto(item,skyOneTwo.get(0),skyOneTwo.get(1),regionName));

            }
           return new ResponseEntity<>(weatherResponseService.getWeatherResult(result), HttpStatus.OK);
        } else {
            List<ApiWeatherMidAmDto> result = new ArrayList<>();
            JsonArray jsonItemList = weatherMidApiService.midWeather(region.getRegid());
            for (Object o : jsonItemList) {
                JsonObject item = (JsonObject) o;
                result.add(new ApiWeatherMidAmDto(item,skyOneTwo.get(0),skyOneTwo.get(1),regionName));
            }
            return new ResponseEntity<>(weatherResponseService.getWeatherResult(result), HttpStatus.OK);
        }
    }
}
