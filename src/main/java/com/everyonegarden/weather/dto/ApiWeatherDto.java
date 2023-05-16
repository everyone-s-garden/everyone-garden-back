package com.everyonegarden.weather.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiWeatherDto {


    //baseDate":"20230516","baseTime":"2000","category":"TMP","fcstDate":"20230516","fcstTime":"2100","fcstValue":"22","nx":60,"ny":125}

    private String baseDate; // 날짜
    private String baseTime; // 발료 시간
    private String category; // 자료 구분 코드
    private String fcstDate; // 예보 일자
    private String fcstTime; // 예보 시각
    private String fcstValue; // 자료에 대한 값
    private String nx; // 경도
    private String ny; // 위도



}
