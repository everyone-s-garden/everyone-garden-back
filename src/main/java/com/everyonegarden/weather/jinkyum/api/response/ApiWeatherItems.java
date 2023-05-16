package com.everyonegarden.weather.jinkyum.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ApiWeatherItems {

    private List<ApiWeatherItem> item;

}
