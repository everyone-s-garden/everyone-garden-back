package com.everyonegarden.garden.response;

import com.everyonegarden.garden.GardenType;
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
    private String longitude;
    private String latitude;

    private String link;
    private Integer price;
}
