package com.everyonegarden.weather.service.shortterm.all;

import com.everyonegarden.weather.service.WeatherFetchService;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class WeatherAllApiService {

    //EndPoint
    @Value("${api.weatherAll.url}") private String apiUrl ;
    //홈페이지에서 받은 키
    @Value("${api.weather.secret}") private String serviceKey ;

    private final WeatherFetchService weatherFetchService;

    public JsonArray allWeather(String nx, String ny) throws Exception {

        String baseDate = weatherFetchService.getTodayDate();

        int presentTime = Integer.parseInt(weatherFetchService.getTime())-1;
        String baseTime = presentTime+"30";
        String type = "JSON";




        StringBuilder urlBuilder = new StringBuilder(apiUrl);

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&pageNo=1&numOfRows=10000");
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("base_date","UTF-8")+"="+URLEncoder.encode(baseDate,"UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("base_time","UTF-8")+"="+URLEncoder.encode(baseTime,"UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("nx","UTF-8")+"="+URLEncoder.encode(nx,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("ny","UTF-8")+"="+URLEncoder.encode(ny,"UTF-8"));//위도

        String makeUrl = urlBuilder.toString();

        //System.out.println(makeUrl);
        return weatherFetchService.fetchWeather(makeUrl);
    }
}
