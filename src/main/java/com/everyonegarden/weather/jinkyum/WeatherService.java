package com.everyonegarden.weather.jinkyum;

import com.everyonegarden.weather.jinkyum.api.response.ApiWeatherItem;
import com.everyonegarden.weather.jinkyum.api.response.ApiWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WeatherService {

    @Value("${api.weather.url}") private String apiEndpoint;
    @Value("${api.weather.secret}") private String apiSecret;

    @Qualifier("weatherApi")
    private final RestTemplate restTemplate;

    public List<ApiWeatherItem> getWeatherData() {
        String url = String.format(
                "%s?" +
                        "serviceKey=%s&" +
                        "base_date=20230516&" +
                        "&pageNo=1&numOfRows=10000&dataType=JSON&base_time=2000&nx=60&ny=125",

                apiEndpoint,
                apiSecret
        );

        try {
            RequestEntity<Void> request = new RequestEntity<>(HttpMethod.GET, new URI(url));
            ResponseEntity<ApiWeatherResponse> response = restTemplate.exchange(request, ApiWeatherResponse.class);

            return response.getBody().getResponse().getBody().getItems().getItem();
        } catch (Exception exception) {
            return List.of();
        }

    }

}
