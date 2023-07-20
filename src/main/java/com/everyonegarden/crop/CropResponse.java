package com.everyonegarden.crop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class CropResponse {

    private Long id;
    private String name;
    private String description;
    private String link;
    private String image;

    public static CropResponse of(Crop crop) {
        return CropResponse.builder()
                .id(crop.getCropId())
                .name(crop.getName())
                .description(crop.getDescription())
                .link(crop.getLink())
                .image(crop.getImage())
                .build();
    }

}