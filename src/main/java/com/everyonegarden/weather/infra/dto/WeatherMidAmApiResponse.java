package com.everyonegarden.weather.infra.dto;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class WeatherMidAmApiResponse implements WeatherMidResponse {

    private String wf1Am;
    private String wf2Am;
    private String wf3Am;
    private String wf4Am;
    private String wf5Am;
    private String wf6Am;
    private String wf7Am;
    private String regionName;

    public WeatherMidAmApiResponse(JsonObject item, String wf1Am, String wf2Am, String regionName) {

        this.wf1Am = wf1Am;
        this.wf2Am = wf2Am;
        this.wf3Am = item.get("wf3Am").getAsString();
        this.wf4Am = item.get("wf4Am").getAsString();
        this.wf5Am = item.get("wf5Am").getAsString();
        this.wf6Am = item.get("wf6Am").getAsString();
        this.wf7Am = item.get("wf7Am").getAsString();
        this.regionName = regionName;
    }

}
