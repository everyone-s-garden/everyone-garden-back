package com.everyonegarden.global;

import com.everyonegarden.weather.service.TodayTimer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Component
public class UrlBuilder {

    private final TodayTimer todayTimer;

    @Value("${api.weather.secret}")
    private String weatherServiceKey;

    public UrlBuilder(TodayTimer todayTimer) {
        this.todayTimer = todayTimer;
    }

    public String buildWeatherUrl(String apiUrl, String baseTime, String nx, String ny) {
        return UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("serviceKey", weatherServiceKey)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 10000)
                .queryParam("dataType", "JSON")
                .queryParam("base_date", todayTimer.getDay())
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .build(true)
                .encode()
                .toUriString();
    }

    public String buildWeatherUrl(String apiUrl, String tmFc, String regId) {
        return UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("serviceKey", weatherServiceKey)
                .queryParam("dataType", "JSON")
                .queryParam("tmFc", tmFc)
                .queryParam("regId", regId)
                .build(true)
                .encode()
                .toUriString();
    }
}
