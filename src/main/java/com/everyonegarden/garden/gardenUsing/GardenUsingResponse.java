package com.everyonegarden.garden.gardenUsing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenUsingResponse {

    private Long id;
    private String name;
    private String image;

    private String address;
    private Double latitude;
    private Double longitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate useStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate useEndDate;

    public static GardenUsingResponse of(GardenUsing gardenUsing) {
        return GardenUsingResponse.builder()
                .id(gardenUsing.getId())
                .name(gardenUsing.getName())
                .image(gardenUsing.getImage())
                .address(gardenUsing.getAddress())
                .latitude(gardenUsing.getLatitude())
                .longitude(gardenUsing.getLongitude())
                .useStartDate(gardenUsing.getUseStartDate())
                .useEndDate(gardenUsing.getUseEndDate())
                .build();
    }

}
