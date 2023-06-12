package com.everyonegarden.weather.dto;


import com.google.gson.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiWeatherTimeDto extends WeatherDto {


    //baseDate":"20230516","baseTime":"2000","category":"TMP","fcstDate":"20230516","fcstTime":"2100","fcstValue":"22","nx":60,"ny":125}

    private String baseDate; // 날짜

    private String category; // 자료 구분 코드
    private String fcstDate; // 예보 일자
    private String fcstTime; // 예보 시각
    private String fcstValue; // 자료에 대한 값

    private String regionName;

    public ApiWeatherTimeDto(JsonObject item, String regionName){

        this.baseDate=item.get("baseDate").getAsString();
        this.category=item.get("category").getAsString();
        this.fcstDate=item.get("fcstDate").getAsString();
        this.fcstTime=item.get("fcstTime").getAsString();
        this.fcstValue=item.get("fcstValue").getAsString();
        this.regionName=makeRegion(regionName);
    }

}
