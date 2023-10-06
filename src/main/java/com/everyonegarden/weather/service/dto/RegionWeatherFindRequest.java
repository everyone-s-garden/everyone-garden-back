package com.everyonegarden.weather.service.dto;

import lombok.Getter;

@Getter
public class RegionWeatherFindRequest {

    private String nx;
    private String ny;
    private String regionId;
    private String regionName;

    public RegionWeatherFindRequest(String nx, String ny, String regionId, String regionName) {
        this.nx = nx;
        this.ny = ny;
        this.regionId = regionId;
        this.regionName = regionName;
    }

}
