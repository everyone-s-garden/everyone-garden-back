package com.everyonegarden.weather.service;

import com.everyonegarden.weather.dto.ApiWeatherDto;

import com.fasterxml.jackson.core.JsonParser;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URI;

import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ssl.TrustStrategy;
import javax.net.ssl.SSLContext;



//단기예보
@Service
@RequiredArgsConstructor
public class WeatherFetchService {

   private final RestTemplate restTemplate ;
   private final ApiWeatherResponseService apiWeatherResponseService;

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


        URI uri = new URI(makeUrl);
        RestTemplate restTemplate = this.makeRestTemplate();
        String jsonString = restTemplate.getForObject(uri,String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

        //가장 큰 Json 객체 response 가져오기
        JSONObject jsonResponse = (JSONObject) jsonObject.get("response");
        // body부분 파싱
        JSONObject jsonBody = (JSONObject) jsonResponse.get("body");
        // items 파싱
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");

        // itmes는 JSON -> 배열로 가져오기
        JSONArray jsonItemList = (JSONArray) jsonItems.get("item");

        List<ApiWeatherDto> result = new ArrayList<>();

        for(Object o : jsonItemList){
            JSONObject item = (JSONObject) o;
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



    //restTemplate ssl 인증 무시하는 메서드
    private RestTemplate makeRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        requestFactory.setConnectTimeout(3 * 1000);

        requestFactory.setReadTimeout(3 * 1000);

        return new RestTemplate(requestFactory);
    }


    private ApiWeatherDto makeWeatherDto(JSONObject item) {
        /*

    private String baseDate; // 날짜
    private String baseTime; // 발료 시간
    private String category; // 자료 구분 코드
    private String fcstDate; // 예보 일자
    private String fcstTime; // 예보 시각
    private String fcstValue; // 자료에 대한 값
    private String nx; // 경도
    private String ny; // 위도
         */
        //{"baseDate":"20230516","baseTime":"2000","category":"TMP","fcstDate":"20230516","fcstTime":"2100","fcstValue":"22","nx":60,"ny":125}

        ApiWeatherDto dto = ApiWeatherDto.builder()
                .baseDate((String) item.get("baseDate"))
                .baseTime((String)item.get("baseTime"))
                .category((String)item.get("category"))
                .fcstDate((String)item.get("fcstDate"))
                .fcstTime((String)item.get("fcstTime"))
                .fcstValue((String) item.get("fcstValue"))
                .nx((String) item.get("nx"))
                .ny((String) item.get("ny"))
                .build();

        return dto;
    }
}
