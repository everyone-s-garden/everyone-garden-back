package com.everyonegarden.weather.infra.dto;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class WeatherAllApiResponse {

    private String baseDate;
    private String category;
    private String obsrValue;
    private String regionName;

    public WeatherAllApiResponse(JsonObject item, String regionName) {
        this.baseDate = item.get("baseDate").getAsString();
        this.category = item.get("category").getAsString();
        this.obsrValue = item.get("obsrValue").getAsString();
        this.regionName = regionName;
    }

    public WeatherAllApiResponse(String baseDate, String category, String obsrValue, String regionName) {
        this.baseDate = baseDate;
        this.category = category;
        this.obsrValue = obsrValue;
        this.regionName = regionName;
    }
}
