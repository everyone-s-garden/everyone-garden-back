package com.everyonegarden.location.dto;


import lombok.Getter;

@Getter
public class LocationResponse {

    private String position;
    private Double latitude;
    private Double longitude;

    public LocationResponse(String position, Double latitude, Double longitude) {
        this.position = position;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
