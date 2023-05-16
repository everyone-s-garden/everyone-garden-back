package com.everyonegarden.weather.jinkyum.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherApiRestTemplate {

    @Bean
    public RestTemplate weatherApi() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new WeatherApiResponseInterceptor());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }

}
