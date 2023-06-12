package com.everyonegarden.weather.dto;

public class WeatherDto {
    public String makeRegion(String regionName){
        if(regionName.length()==4){
            return regionName.substring(0,1)+regionName.substring(2,3);
        }

        return regionName.substring(0,2);
    }
}
