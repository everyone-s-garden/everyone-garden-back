package com.everyonegarden.weather.jinkyum.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ApiWeatherResponseResponse {

    private ApiWeatherHeader header;
    private ApiWeatherBody body;

}
