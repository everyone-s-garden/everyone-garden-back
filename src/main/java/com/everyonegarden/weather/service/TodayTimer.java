package com.everyonegarden.weather.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TodayTimer {

    private final ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
    private final ZonedDateTime seoulDateTime = ZonedDateTime.now(seoulZoneId);

    public String getDay() {
        LocalDate today = seoulDateTime.toLocalDate();
        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getTomorrow() {
        LocalDate today = seoulDateTime.toLocalDate();
        LocalDate tomorrow = today.plusDays(1);

        return tomorrow.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getTmFc() {
        String today = getDay();
        int timeFormat = Integer.parseInt(getTime());

        return today + (timeFormat >= 6 && timeFormat <= 17 ? "0600" : "1800");
    }

    public String getTime() {
        LocalTime localTime = seoulDateTime.toLocalTime();

        return localTime.format(DateTimeFormatter.ofPattern("HH"));
    }

}
