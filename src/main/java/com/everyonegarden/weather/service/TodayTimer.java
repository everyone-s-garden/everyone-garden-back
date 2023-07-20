package com.everyonegarden.weather.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TodayTimer {

    ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
    ZonedDateTime seoulDateTime = ZonedDateTime.now(seoulZoneId);


    public String getTime() {
        LocalTime localTime = seoulDateTime.toLocalTime();

        return  localTime.format(DateTimeFormatter.ofPattern("HH"));
    }

    public String getDay() {
        LocalDate today = seoulDateTime.toLocalDate();
        return today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getTomorrow() {
        LocalDate today = seoulDateTime.toLocalDate();
        LocalDate tomorrow = today.plusDays(1);

        return tomorrow.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
