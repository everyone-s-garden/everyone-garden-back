package com.everyonegarden.weather.service.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AllRegionWeatherRequests {

    private List<RegionWeatherFindRequest> regionWeatherFindRequests;

    public AllRegionWeatherRequests(List<RegionWeatherFindRequest> regionWeatherFindRequests) {
        this.regionWeatherFindRequests = regionWeatherFindRequests;
    }

}
