package com.everyonegarden.weather.infra;

import com.everyonegarden.global.ApiEndpointProvider;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.StringReader;

@Service
public class WeatherApiFetcher {

    private static final DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
    private final WebClient webClient;
    private final ApiEndpointProvider apiEndpointProvider;

    public WeatherApiFetcher(WebClient.Builder webClientBuilder, ApiEndpointProvider apiEndpointProvider) {
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        this.webClient = webClientBuilder
                .uriBuilderFactory(factory)
                .build();
        this.apiEndpointProvider = apiEndpointProvider;
    }

    public JsonArray fetchWeekWeather(String regionId) {
        return fetchWeatherData(apiEndpointProvider.getMidWeatherUrl(regionId));
    }

    public JsonArray fetchTimeWeather(String nx, String ny) {
        return fetchWeatherData(apiEndpointProvider.getTimeWeatherUrl(nx, ny));
    }

    public JsonArray fetchAllWeather(String nx, String ny) {
        return fetchWeatherData(apiEndpointProvider.getAllWeatherUrl(nx, ny));
    }

    private JsonArray fetchWeatherData(String apiUrl) {
        String responseBody = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("API Response: " + responseBody);

        return parseWeatherData(responseBody);
    }

    private JsonArray parseWeatherData(String responseBody) {
        JsonReader reader = new JsonReader(new StringReader(responseBody));
        reader.setLenient(true);
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

        JsonObject jsonResponse = jsonObject.getAsJsonObject("response")
                .getAsJsonObject("body")
                .getAsJsonObject("items");

        return jsonResponse.getAsJsonArray("item");
    }
}
