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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//단기예보
public class WeatherApi {

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

    public String getWeather(String nx, String ny) throws IOException, ParseException {


        String baseDate = getTodayDate();
        String baseTime = getNearTime(); // 조회 시간  0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
        String type = "JSON";


        System.out.println(apiUrl);
        System.out.println(serviceKey);

        StringBuilder urlBuilder = new StringBuilder(apiUrl);

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&pageNo=1&numOfRows=10000");
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("base_date","UTF-8")+"="+URLEncoder.encode(baseDate,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("base_time","UTF-8")+"="+URLEncoder.encode(baseTime,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("nx","UTF-8")+"="+URLEncoder.encode(nx,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("ny","UTF-8")+"="+URLEncoder.encode(ny,"UTF-8"));//위도



        /*
         * GET 방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type","application/json");

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


        return result;
    }

    /*
    * 현재 날짜를 구하는 메서드
    */
    static String getTodayDate (){
        //현재 날짜
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 포맷 적용
        String today = now.format(formatter);
        return today;
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
