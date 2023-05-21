package com.everyonegarden.weather.service;

import com.everyonegarden.weather.dto.ApiWeatherDto;

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
import java.util.ArrayList;
import java.util.List;





//단기예보
@Service
@RequiredArgsConstructor
public class WeatherFetchService {

   private final RestTemplate restTemplate ;
   private final WeatherResponseService apiWeatherResponseService;

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



    public List<ApiWeatherDto> getWeather(String nx, String ny) throws Exception {




        String baseDate = getTodayDate();
        String baseTime = getNearTime(); // 조회 시간  0200, 0500, 0800, 1100, 1400, 1700, 2000, 2300 (1일 8회)
        String type = "JSON";


        System.out.println(apiUrl);
        System.out.println(serviceKey);

        StringBuilder urlBuilder = new StringBuilder(apiUrl);

        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&pageNo=1&numOfRows=10000");
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("base_date","UTF-8")+"="+URLEncoder.encode(baseDate,"UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("base_time","UTF-8")+"="+URLEncoder.encode(baseTime,"UTF-8"));
        urlBuilder.append("&"+URLEncoder.encode("nx","UTF-8")+"="+URLEncoder.encode(nx,"UTF-8"));//경도
        urlBuilder.append("&"+URLEncoder.encode("ny","UTF-8")+"="+URLEncoder.encode(ny,"UTF-8"));//위도



        String makeUrl = urlBuilder.toString();


        System.out.println(makeUrl);
        URI uri = new URI(makeUrl);
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri,String.class);

        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonObject jsonResponse = (JsonObject) jsonObject.get("response");
        JsonObject jsonBody = (JsonObject) jsonResponse.get("body");
        JsonObject jsonItems = (JsonObject) jsonBody.get("items");


        // itmes는 JSON -> 배열로 가져오기
        JsonArray jsonItemList = (JsonArray) jsonItems.get("item");

        List<ApiWeatherDto> result = new ArrayList<>();

        for(Object o : jsonItemList){
            JsonObject item = (JsonObject) o;
            if(check(item))
                result.add(makeWeatherDto(item));
        }


        System.out.println(urlBuilder);

        return result;
    }

    /*1`
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

    /*
     * 일일 평균 온도, 하늘 상태, 강수량에 해당하는 카테고리인지 확인
     */

    public boolean check(JsonObject item) {

        String target = item.get("category").getAsString();

        if (target.equals("PCP") ||
                target.equals("SKY") ||
                target.equals("TMP")
        ) return true;


        return false;
    }

    private ApiWeatherDto makeWeatherDto(JsonObject item) {

        ApiWeatherDto dto = ApiWeatherDto.builder()
                .baseDate(item.get("baseDate").getAsString())
                .baseTime(item.get("baseTime").getAsString())
                .category(item.get("category").getAsString())
                .fcstDate(item.get("fcstDate").getAsString())
                .fcstTime(item.get("fcstTime").getAsString())
                .fcstValue(item.get("fcstValue").getAsString())
                .nx(item.get("nx").getAsString())
                .ny(item.get("ny").getAsString())
                .build();

        return dto;

    }
}
