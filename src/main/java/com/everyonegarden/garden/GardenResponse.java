package com.everyonegarden.garden;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenResponse {
    private Long id;

    private String name;
    private GardenType type;

    private String address;
    private double longitude;
    private double latitude;

    private String link;
    private Integer price;

    public static GardenResponse of(Garden garden) {
        return GardenResponse.builder()
                .id(garden.getId())
                .name(garden.getName())
                .type(garden.getType())
                .address(garden.getAddress())
                .longitude(garden.getLongitude())
                .latitude(garden.getLatitude())
                .link(garden.getLink())
                .price(garden.getPrice())
                .build();
    }

}
