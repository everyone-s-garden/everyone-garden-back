package com.everyonegarden.weather.infra.dto;

import com.google.gson.JsonObject;

import lombok.Getter;

@Getter
public class WeatherMidPmApiResponse implements WeatherMidResponse {

    private String wf1Pm;
    private String wf2Pm;
    private String wf3Pm;
    private String wf4Pm;
    private String wf5Pm;
    private String wf6Pm;
    private String wf7Pm;
    private String regionName;

    public WeatherMidPmApiResponse(JsonObject item, String wf1Pm, String wf2Pm, String regionName) {
        this.wf1Pm = wf1Pm;
        this.wf2Pm = wf2Pm;
        this.wf3Pm = item.get("wf3Pm").getAsString();
        this.wf4Pm = item.get("wf4Pm").getAsString();
        this.wf5Pm = item.get("wf5Pm").getAsString();
        this.wf6Pm = item.get("wf6Pm").getAsString();
        this.wf7Pm = item.get("wf7Pm").getAsString();
        this.regionName = regionName;
    }

    public WeatherMidPmApiResponse(String wf1Pm, String wf2Pm, String wf3Pm, String wf4Pm,
                                   String wf5Pm, String wf6Pm, String wf7Pm, String regionName) {
        this.wf1Pm = wf1Pm;
        this.wf2Pm = wf2Pm;
        this.wf3Pm = wf3Pm;
        this.wf4Pm = wf4Pm;
        this.wf5Pm = wf5Pm;
        this.wf6Pm = wf6Pm;
        this.wf7Pm = wf7Pm;
        this.regionName = regionName;
    }

}
