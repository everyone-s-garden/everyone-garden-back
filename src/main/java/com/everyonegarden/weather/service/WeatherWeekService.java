package com.everyonegarden.weather.service;

import com.everyonegarden.weather.infra.WeatherApiFetcher;
import com.everyonegarden.weather.infra.dto.WeatherAllApiResponse;
import com.everyonegarden.weather.infra.dto.WeatherApiResult;
import com.everyonegarden.weather.infra.dto.WeatherTimeApiResponse;
import com.everyonegarden.weather.service.dto.AllRegionWeatherRequests;
import com.everyonegarden.weather.infra.dto.WeatherMidResponse;
import com.everyonegarden.weather.service.dto.RegionWeatherFindRequest;

import com.everyonegarden.weather.service.mapper.WeatherDtoMapper;
import com.everyonegarden.weather.service.dto.FirstAndSecondDay;
import com.google.gson.JsonArray;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherWeekService {

    private final WeatherApiFetcher weatherApiFetcher;
    private final FirstAndSecondDayWeatherProvider firstAndSecondDayWeatherProvider;
    private final TodayTimer todayTimer;
    private final WeatherDtoMapper weatherDtoMapper;

    public WeatherWeekService(WeatherApiFetcher weatherApiFetcher,
                              FirstAndSecondDayWeatherProvider firstAndSecondDayWeatherProvider,
                              TodayTimer todayTimer,
                              WeatherDtoMapper weatherDtoMapper) {
        this.weatherApiFetcher = weatherApiFetcher;
        this.firstAndSecondDayWeatherProvider = firstAndSecondDayWeatherProvider;
        this.todayTimer = todayTimer;
        this.weatherDtoMapper = weatherDtoMapper;
    }

    public List<WeatherMidResponse> getWeekWeather(RegionWeatherFindRequest region) {
        FirstAndSecondDay skyOneTwo = firstAndSecondDayWeatherProvider.getSkyOneTwo(region.getNx(), region.getNy());

        int timeFormat = Integer.parseInt(todayTimer.getTime());
        JsonArray jsonItemList = weatherApiFetcher.fetchWeekWeather(region.getRegionId());

        return weatherDtoMapper.toWeatherMidResponses(jsonItemList, timeFormat, region.getRegionName(), skyOneTwo);
    }

    public List<WeatherTimeApiResponse> getRegionWeather(RegionWeatherFindRequest region) {
        JsonArray jsonItemList = weatherApiFetcher.fetchTimeWeather(region.getNx(), region.getNy());

        return weatherDtoMapper.toWeatherTimeApiResponse(jsonItemList, region.getRegionName());
    }

    public WeatherApiResult getAllRegionWeather(AllRegionWeatherRequests allRegion) {
        List<WeatherAllApiResponse> result = fetchDataForAllRegions(allRegion);

        return weatherDtoMapper.toWeatherApiResult(groupDataByRegionName(result));
    }

    private List<WeatherAllApiResponse> fetchDataForAllRegions(AllRegionWeatherRequests allRegion) {
        List<WeatherAllApiResponse> result = new ArrayList<>();
        for (RegionWeatherFindRequest region : allRegion.getRegionWeatherFindRequests()) {

            String regionName = region.getRegionName();
            JsonArray jsonItemList = weatherApiFetcher.fetchAllWeather(region.getNx(), region.getNy());

            weatherDtoMapper.toWeatherAllApiResponse(jsonItemList, regionName).forEach(r -> result.add(r));
        }

        return result;
    }

    private Map<String, List<WeatherAllApiResponse>> groupDataByRegionName(List<WeatherAllApiResponse> data) {
        return data.stream()
                .collect(Collectors.groupingBy(WeatherAllApiResponse::getRegionName));
    }

}
