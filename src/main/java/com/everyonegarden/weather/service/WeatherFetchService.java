package com.everyonegarden.weather.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.*;
import java.time.format.DateTimeFormatter;

//단기예보
@Service
@RequiredArgsConstructor
public class WeatherFetchService {
    public JsonArray fetchWeather(String makeUrl) throws Exception {
        System.out.println("makeUrl=" + makeUrl);
        URI uri = new URI(makeUrl);
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject jsonResponse = (JsonObject) jsonObject.get("response");
        JsonObject jsonBody = (JsonObject) jsonResponse.get("body");
        JsonObject jsonItems = (JsonObject) jsonBody.get("items");

        // itmes는 JSON -> 배열로 가져오기
        JsonArray jsonItemList = (JsonArray) jsonItems.get("item");
        return jsonItemList;
    }

    public String getTodayDate() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime zoneDateTime = now.atZone(zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDateTime = zoneDateTime.format(formatter);
        return formattedDateTime;
    }

    public String getTime() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime zoneDateTime = now.atZone(zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        String formattedDateTime = zoneDateTime.format(formatter);

        return formattedDateTime;
    }
}
