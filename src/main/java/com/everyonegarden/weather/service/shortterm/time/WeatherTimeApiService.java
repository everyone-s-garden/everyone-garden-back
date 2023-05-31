package com.everyonegarden.weather.service.shortterm.time;

import com.everyonegarden.weather.service.WeatherFetchService;

import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherTimeApiService {


    private final WeatherFetchService weatherFetchService;

    //EndPoint
    @Value("${api.weather.url}") private String apiUrl ;
    //홈페이지에서 받은 키
    @Value("${api.weather.secret}") private String serviceKey ;


    static String[] time={"2300","2300",
            "0200","0200","0200",
            "0500", "0500", "0500",
            "0800", "0800","0800",
            "1100", "1100","1100",
            "1400", "1400","1400",
            "1700", "1700","1700",
            "2000", "2000","2000","2300"};

    public JsonArray shortWeather(String nx, String ny) throws Exception {

        String baseDate = weatherFetchService.getTodayDate();
        int index = Integer.parseInt(weatherFetchService.getTime());
        String baseTime =time[index]; // 조회 시간  0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
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

        return weatherFetchService.fetchWeather(makeUrl);
    }

    /*
     * 현재 시간을 기준으로
     * 조회 시간  0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
     * 중 가까운 조회시간 구하기
     */
    static String getNearTime (){
        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");
        int index = Integer.parseInt(now.format(formatter));

        return time[index];
    }
}
