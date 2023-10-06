package com.everyonegarden.region.infra;

import com.everyonegarden.region.entity.Region;
import com.everyonegarden.global.ApiEndpointProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ReverseGeoFetcher {

    private final WebClient.Builder webClientBuilder;

    private final ApiEndpointProvider apiEndpointProvider;

    public ReverseGeoFetcher(WebClient.Builder webClientBuilder, ApiEndpointProvider apiEndpointProvider) {
        this.webClientBuilder = webClientBuilder;
        this.apiEndpointProvider = apiEndpointProvider;
    }

    public String getRegionName(String lng, String lat) {
        HttpHeaders httpHeaders = apiEndpointProvider.createGeoHttpHeaders();
        String url = apiEndpointProvider.getReverseGeoUrl(Region.formatRegionName(lng,lat));

        String responseBody = webClientBuilder.build()
                .get()
                .uri(url)
                .headers(headers -> headers.putAll(httpHeaders))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractRegionNameFromResponse(responseBody);
    }

    private String extractRegionNameFromResponse(String responseBody) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
            JsonNode sidoNode = jsonNode.at("/results/0/region/area1/name");
            return sidoNode.asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
