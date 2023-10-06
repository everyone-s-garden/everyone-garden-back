package com.everyonegarden.weather.infra.dto;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class WeatherTimeApiResponse {

    private String baseDate;
    private String category;
    private String fcstDate;
    private String fcstTime;
    private String fcstValue;
    private String regionName;

    public WeatherTimeApiResponse(JsonObject item, String regionName) {
        this.baseDate = item.get("baseDate").getAsString();
        this.category = item.get("category").getAsString();
        this.fcstDate = item.get("fcstDate").getAsString();
        this.fcstTime = item.get("fcstTime").getAsString();
        this.fcstValue = item.get("fcstValue").getAsString();
        this.regionName = regionName;
    }

    public WeatherTimeApiResponse(String baseDate, String category, String fcstDate, String fcstTime, String fcstValue, String regionName) {
        this.baseDate = baseDate;
        this.category = category;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.fcstValue = fcstValue;
        this.regionName = regionName;
    }

}
