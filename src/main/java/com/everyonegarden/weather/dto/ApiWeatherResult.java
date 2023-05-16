package com.everyonegarden.weather.dto;


import lombok.Data;

import java.util.List;

@Data
public class ApiWeatherResult<T> {

    private boolean success;
    private int code;
    private T data;

}
