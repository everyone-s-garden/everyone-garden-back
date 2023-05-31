package com.everyonegarden.weather.service;



import com.everyonegarden.weather.service.WeatherResponseService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URI;

import java.net.URLEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;



//단기예보
@Service
@RequiredArgsConstructor
public class WeatherFetchService {


    public JsonArray fetchWeather(String makeUrl) throws Exception {


        URI uri = new URI(makeUrl);
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri,String.class);

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject jsonResponse = (JsonObject) jsonObject.get("response");
        JsonObject jsonBody = (JsonObject) jsonResponse.get("body");
        JsonObject jsonItems = (JsonObject) jsonBody.get("items");


        // itmes는 JSON -> 배열로 가져오기
        JsonArray jsonItemList = (JsonArray) jsonItems.get("item");

        //System.out.println(urlBuilder);

        return jsonItemList;
    }

    public String getTodayDate (){
        //현재 날짜
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 포맷 적용
        String today = now.format(formatter);
        return today;
    }
    public String getTime (){
        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String time  = now.format(formatter);

        return time;
    }

}
