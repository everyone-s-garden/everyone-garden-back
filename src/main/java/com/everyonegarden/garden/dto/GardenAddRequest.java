package com.everyonegarden.garden.dto;

import com.everyonegarden.garden.Garden;
import com.everyonegarden.garden.GardenStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenAddRequest {

    private String address;
    private Double latitude;
    private Double longitude;

    private String name;
    private String link;
    private String price;
    private String size;

    private List<String> images;

    private String content;
    private String status;

    public Garden toEntity(Long memberId) {
        return Garden.builder()
                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .name(name)
                .link(link)
                .price(price)
                .size(size)

                .content(content)
                .status(GardenStatus.valueOf(status.toUpperCase()))

                .build();
    }

}