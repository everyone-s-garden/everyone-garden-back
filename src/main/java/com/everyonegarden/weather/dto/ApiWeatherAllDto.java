package com.everyonegarden.weather.dto;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class ApiWeatherAllDto extends WeatherDto{
    private String baseDate; // 날짜
    private String category; // 자료 구분 코드

    private String obsrValue; // 자료에 대한 값
    private String regionName;

    public ApiWeatherAllDto(JsonObject item, String regionName){

        this.baseDate=item.get("baseDate").getAsString();
        this.category=item.get("category").getAsString();
        this.obsrValue=item.get("obsrValue").getAsString();
        this.regionName=regionName;
    }
}
