package com.everyonegarden.weather.facade.mapper;

import com.everyonegarden.region.service.dto.RegionFindAllRequests;
import com.everyonegarden.region.service.dto.RegionFindRequest;
import com.everyonegarden.weather.service.dto.AllRegionWeatherRequests;
import com.everyonegarden.weather.service.dto.RegionWeatherFindRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class WeatherFacadeMapper {

    public RegionWeatherFindRequest toRegionWeatherFindRequest(RegionFindRequest regionFindRequest) {
        return new RegionWeatherFindRequest(
                regionFindRequest.getNx(),
                regionFindRequest.getNy(),
                regionFindRequest.getRegionId(),
                regionFindRequest.getRegionName()
        );
    }

    public AllRegionWeatherRequests toAllRegionWeatherRequests(RegionFindAllRequests regionFindAllRequests){
        return new AllRegionWeatherRequests(
                regionFindAllRequests.getRegionFindRequests()
                        .stream()
                        .map(this::toRegionWeatherFindRequest)
                        .collect(Collectors.toList())
        );
    }
}
