package com.everyonegarden.weather.dto;

import com.google.gson.JsonObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiWeatherMidPmDto extends WeatherDto {

     private String wf1Pm;
     private String wf2Pm;
     private String wf3Pm;
     private String wf4Pm;
     private String wf5Pm;
     private String wf6Pm;
     private String wf7Pm;
     private String regionName;
     public ApiWeatherMidPmDto(JsonObject item, String wf1Pm, String wf2Pm, String regionName){

          this.wf1Pm = wf1Pm;
          this.wf2Pm = wf2Pm;
          this.wf3Pm = item.get("wf3Pm").getAsString();
          this.wf4Pm = item.get("wf4Pm").getAsString();
          this.wf5Pm = item.get("wf5Pm").getAsString();
          this.wf6Pm = item.get("wf6Pm").getAsString();
          this.wf7Pm = item.get("wf7Pm").getAsString();
          this.regionName = makeRegion(regionName);
     }



}
