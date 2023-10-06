package com.everyonegarden.weather.facade;

import com.everyonegarden.region.service.RegionService;
import com.everyonegarden.weather.infra.dto.WeatherApiResult;
import com.everyonegarden.weather.infra.dto.WeatherTimeApiResponse;
import com.everyonegarden.weather.service.dto.AllRegionWeatherRequests;
import com.everyonegarden.weather.infra.dto.WeatherMidResponse;
import com.everyonegarden.weather.facade.mapper.WeatherFacadeMapper;
import com.everyonegarden.weather.service.dto.RegionWeatherFindRequest;
import com.everyonegarden.weather.service.WeatherService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherFacade {

    private final RegionService regionService;
    private final WeatherFacadeMapper weatherFacadeMapper;
    private final WeatherService weatherService;

    public WeatherFacade(RegionService regionService,
                         WeatherFacadeMapper weatherFacadeMapper,
                         WeatherService weatherService) {
        this.regionService = regionService;
        this.weatherFacadeMapper = weatherFacadeMapper;
        this.weatherService = weatherService;
    }

    public WeatherApiResult getAllRegionWeather() {

        AllRegionWeatherRequests allRegionWeatherRequests = weatherFacadeMapper.toAllRegionWeatherRequests(regionService.findAllBy());
        return weatherService.getAllRegionWeather(allRegionWeatherRequests);
    }

    public List<WeatherTimeApiResponse> getRegionWeather(String lng, String lat) {
        RegionWeatherFindRequest regionWeatherFindRequest = weatherFacadeMapper.toRegionWeatherFindRequest(regionService.findRegion(lng, lat));

        return weatherService.getRegionWeather(regionWeatherFindRequest);
    }

    public List<WeatherMidResponse> getWeekWeather(String lng, String lat) {
        RegionWeatherFindRequest regionWeatherFindRequest = weatherFacadeMapper.toRegionWeatherFindRequest(regionService.findRegion(lng, lat));

        return weatherService.getWeekWeather(regionWeatherFindRequest);
    }
}
