package com.everyonegarden.weather.service.dto;

import lombok.Getter;

@Getter
public class FirstAndSecondDay {

    String firstDayWeather;
    String secondDayWeather;

    public FirstAndSecondDay(String firstDayWeather, String secondDayWeather) {
        this.firstDayWeather = firstDayWeather;
        this.secondDayWeather = secondDayWeather;
    }

}
