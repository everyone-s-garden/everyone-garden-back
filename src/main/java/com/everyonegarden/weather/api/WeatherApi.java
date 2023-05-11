package com.everyonegarden.weather.api;

import com.everyonegarden.weather.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;

import java.net.URLEncoder;

//단기예보
public class WeatherApi {

    //EndPoint
    @Value("${api.weather.url}") private String apiUrl ;
    //홈페이지에서 받은 키
    @Value("${api.weather.secret}") private String serviceKey ;

    public String getWeather(String nx, String ny) {

        String baseDate = "20230510";// 조회 날짜
        String baseTime = "1100"; // 조회 시간
        String type = "json";


        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        //urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);


        return "";
    }
}
