package com.everyonegarden.global;

import com.everyonegarden.weather.service.TodayTimer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ApiEndpointProvider {

    private static final String[] TIME = {"2300", "2300", "0200", "0200", "0200", "0500", "0500", "0500",
            "0800", "0800", "0800", "1100", "1100", "1100", "1400", "1400", "1400", "1700", "1700", "1700",
            "2000", "2000", "2000", "2300"};

    private final TodayTimer todayTimer;
    private final UrlBuilder urlBuilder;

    @Value("${api.weatherWeek.url}")
    private String weatherWeekApiUrl;

    @Value("${api.weather.url}")
    private String weatherApiUrl;

    @Value("${api.weatherAll.url}")
    private String weatherAllApiUrl;

    @Value("${api.reverseGeo.url}")
    private String reverseGeoApiUrl;

    @Value("${api.reverseGeo.id}")
    private String geoServiceId;

    @Value("${api.reverseGeo.secret}")
    private String geoServiceKey;

    public ApiEndpointProvider(TodayTimer todayTimer, UrlBuilder urlBuilder) {
        this.todayTimer = todayTimer;
        this.urlBuilder = urlBuilder;
    }

    public String getMidWeatherUrl(String regId) {
        return urlBuilder.buildWeatherUrl(weatherWeekApiUrl, todayTimer.getTmFc(), regId);
    }

    public String getReverseGeoUrl(String coords) {
        return String.format("%s?coords=%s&output=json", reverseGeoApiUrl, coords);
    }

    public String getTimeWeatherUrl(String nx, String ny) {
        int index = Integer.parseInt(todayTimer.getTime());
        String baseTime = TIME[index];

        System.out.println("여기"+urlBuilder.buildWeatherUrl(weatherApiUrl, baseTime, nx, ny));
        return urlBuilder.buildWeatherUrl(weatherApiUrl, baseTime, nx, ny);
    }

    public String getAllWeatherUrl(String nx, String ny) {
        int presentTime = Integer.parseInt(todayTimer.getTime()) - 2;
        if (presentTime < 10) {
            presentTime = 0 + presentTime;
        }
        String baseTime = presentTime + "00";

        return urlBuilder.buildWeatherUrl(weatherAllApiUrl, baseTime, nx, ny);
    }

    public HttpHeaders createGeoHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-NCP-APIGW-API-KEY-ID", geoServiceId);
        httpHeaders.add("X-NCP-APIGW-API-KEY", geoServiceKey);
        return httpHeaders;
    }

}
