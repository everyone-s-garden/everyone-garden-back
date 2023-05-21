package com.everyonegarden.weather.dto;


import lombok.Data;

@Data
public class ApiWeatherRandomResult<T> {

    private boolean success;
    private int code;
    private String regionName;
    private T data;
}
