package com.everyonegarden.weather.dto;


import lombok.Data;

import java.util.List;

@Data
public class ApiWeatherResponse {

    private boolean success;
    private int code;
    private List<ApiWeatherRow> row;

}
