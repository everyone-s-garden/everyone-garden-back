package com.everyonegarden.weather.service;

import com.everyonegarden.weather.infra.WeatherApiFetcher;

import com.everyonegarden.weather.service.mapper.WeatherDtoMapper;
import com.everyonegarden.weather.service.dto.FirstAndSecondDay;
import com.google.gson.JsonArray;

import org.springframework.stereotype.Service;

@Service
public class FirstAndSecondDayWeatherProvider {

    private final WeatherApiFetcher weatherApiFetcher;
    private final TodayTimer todayTimer;
    private final WeatherDtoMapper weatherDtoMapper;

    public FirstAndSecondDayWeatherProvider(WeatherApiFetcher weatherApiFetcher,
                                            TodayTimer todayTimer, WeatherDtoMapper weatherDtoMapper) {
        this.weatherApiFetcher = weatherApiFetcher;
        this.todayTimer = todayTimer;
        this.weatherDtoMapper = weatherDtoMapper;
    }

    public FirstAndSecondDay getSkyOneTwo(String nx, String ny) {
        String fcstTime = todayTimer.getTime() + "00";
        String oneDay = todayTimer.getDay();
        String twoDay = todayTimer.getTomorrow();

        JsonArray jsonItemList = weatherApiFetcher.fetchTimeWeather(nx, ny);

        return weatherDtoMapper.toFirstAndSecondDay(jsonItemList, fcstTime, oneDay, twoDay);
    }

}
