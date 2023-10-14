package com.everyonegarden.location.service.dto;

import lombok.Getter;

@Getter
public class LocationSearchResponse {

    private String position;
    private Double latitude;
    private Double longitude;

    public LocationSearchResponse(String position, Double latitude, Double longitude) {
        this.position = position;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
