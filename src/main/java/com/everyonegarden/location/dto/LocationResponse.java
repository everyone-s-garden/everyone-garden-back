package com.everyonegarden.location.dto;


import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.dto.GardenResponse;
import com.everyonegarden.location.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class LocationResponse {

    private String position;
    private Double latitude;
    private Double longitude;

    public LocationResponse(String position, Double latitude, Double longitude) {
        this.position = position;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static LocationResponse buildLocationResponse(Location location){
        return LocationResponse.builder()
                .position(location.assembleFullAddress())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
