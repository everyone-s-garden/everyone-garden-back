package com.everyonegarden.weather.api;

import com.everyonegarden.weather.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//단기예보
public class WeatherApi {

    //EndPoint
    @Value("${api.weather.url}") private String apiUrl ;
    //홈페이지에서 받은 키
    @Value("${api.weather.secret}") private String serviceKey ;

    public String getWeather(String nx, String ny) throws IOException, ParseException {

        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 포맷 적용
        String baseDate = now.format(formatter);
        String baseTime = "1100"; // 조회 시간
        String type = "json";


        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&"+URLEncoder.encode("nx","UTF-8")+"="+URLEncoder.encode(nx,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("ny","UTF-8")+"="+URLEncoder.encode(ny,"UTF-8"));//위도
        urlBuilder.append("&"+URLEncoder.encode("baseDate","UTF-8")+"="+URLEncoder.encode(baseDate,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("baseTime","UTF-8")+"="+URLEncoder.encode(baseTime,"UTF-8"));//경도
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));

        /*
         * GET 방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type","application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >=200 && conn.getResponseCode()<=300){
            rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }else{
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while((line=rd.readLine())!=null){
                sb.append(line);
            }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
        System.out.println(result);



        return result;
    }
}
