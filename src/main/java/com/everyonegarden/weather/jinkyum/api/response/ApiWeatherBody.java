package com.everyonegarden.weather.jinkyum.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ApiWeatherBody {

    private String dataType;
    private ApiWeatherItems items;
    private int pageNo;
    private int numOfRows;
    private int totalCount;
}
