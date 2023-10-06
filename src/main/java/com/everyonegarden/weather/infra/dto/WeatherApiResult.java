package com.everyonegarden.weather.infra.dto;

import lombok.Getter;

@Getter
public class WeatherApiResult<T> {

    private boolean success;
    private int code;
    private T data;

    public WeatherApiResult(boolean success, int code, T data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }

}
