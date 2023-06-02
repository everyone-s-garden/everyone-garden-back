package com.everyonegarden.garden.dto;

import com.everyonegarden.garden.Garden;
import com.everyonegarden.garden.GardenStatus;
import com.everyonegarden.garden.GardenType;
import com.everyonegarden.garden.gardenView.GardenView;
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
    private String price;

    private String contact;
    private String size;

    private GardenStatus status;

    public static GardenResponse of(Garden garden) {
        return GardenResponse.builder()
                .id(garden.getGardenId())

                .name(garden.getName())
                .type(garden.getType())
                .link(garden.getLink())
                .price(garden.getPrice())

                .address(garden.getAddress())
                .longitude(garden.getLongitude())
                .latitude(garden.getLatitude())

                .contact(garden.getContact())
                .size(garden.getSize())

                .status(garden.getStatus())

                .build();
    }

    public static GardenResponse of(GardenView gardenView) {
        return GardenResponse.builder()
                .id(gardenView.getGarden().getGardenId())

                .name(gardenView.getGarden().getName())
                .type(gardenView.getGarden().getType())
                .link(gardenView.getGarden().getLink())
                .price(gardenView.getGarden().getPrice())

                .address(gardenView.getGarden().getAddress())
                .latitude(gardenView.getGarden().getLatitude())
                .longitude(gardenView.getGarden().getLongitude())

                .build();
    }

}
