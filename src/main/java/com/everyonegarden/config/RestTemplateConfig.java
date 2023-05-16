package com.everyonegarden.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
        Jaxb2RootElementHttpMessageConverter converter = new Jaxb2RootElementHttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_XML));
        return converter;
    }

    @Bean
    @Primary
    public RestTemplate xmlRestTemplate() {
        return new RestTemplateBuilder()
                .messageConverters(List.of(
                                jaxb2RootElementHttpMessageConverter(),
                                new MappingJackson2HttpMessageConverter(),
                                new StringHttpMessageConverter(StandardCharsets.UTF_8)
                        )
                )
                .build();
    }

}
