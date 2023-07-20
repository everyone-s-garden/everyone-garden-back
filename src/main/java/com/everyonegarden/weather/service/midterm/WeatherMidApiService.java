package com.everyonegarden.weather.service.midterm;

import com.everyonegarden.weather.service.WeatherFetchService;
import com.google.gson.JsonArray;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherMidApiService {


    private final WeatherFetchService weatherFetchService;
    @Value("${api.weatherWeek.url}") private String apiUrl ;
    @Value("${api.weather.secret}") private String serviceKey ;

    public JsonArray midWeather(String regId) throws Exception {

        TodayTimer todayTimer = new TodayTimer();
        String today = todayTimer.getDay();
        int time = Integer.parseInt(todayTimer.getTime());

        String tmFc = makeTmFc(today,time);
        String dataType = "JSON";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("tmFc","UTF-8")+"="+URLEncoder.encode(tmFc,"UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("regId","UTF-8")+"="+URLEncoder.encode(regId,"UTF-8"));


        String makeUrl = urlBuilder.toString();

        return weatherFetchService.fetchWeather(makeUrl);
    }

    //06시, 18시(일 2회)
    // 6시부터 18시 사이이면 6시
    //18시부터 5시사이이면  18시
    static String makeTmFc(String today,int timeformat){

        String time="";
        if(timeformat>=6 && timeformat<=17) time="0600";
        else time="1800";

        return today+time;
    }

}
