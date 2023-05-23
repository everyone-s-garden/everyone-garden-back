package com.everyonegarden.weather.service.reversegeo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ReverseGeoFetchService {
    private final RestTemplate restTemplate;

    @Value("${api.reverseGeo.url}") private String apiUrl ;

    @Value("${api.reverseGeo.id}") private String serviceId ;

    @Value("${api.reverseGeo.secret}") private String serviceKey ;

    public String getRegionName(String lat, String lng){

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        //경도 lng, 위도 lat
        //좌표 (경도, 위도)
        String coords=lng+","+lat;

        String url = String.format(
                "%s?" +
                        "coords=%s&" +
                        "output=json",

                apiUrl,
                coords
        );

        //System.out.println(url);

        // 헤더 정보 세팅하기
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-NCP-APIGW-API-KEY-ID", serviceId);
        httpHeaders.add("X-NCP-APIGW-API-KEY", serviceKey);


        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,  new HttpEntity<>(httpHeaders), String.class);

        ObjectMapper objectMapper = new ObjectMapper();


        String sido="";

        try {

            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            JsonNode SIDO = jsonNode.get("results").get(0).get("region").get("area1").get("name");
            sido=SIDO.toString().replace("\"", "");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return sido;

    }




}

