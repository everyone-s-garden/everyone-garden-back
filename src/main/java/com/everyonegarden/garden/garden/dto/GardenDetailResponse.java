package com.everyonegarden.garden.garden.dto;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenStatus;
import com.everyonegarden.garden.gardenImage.GardenImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenDetailResponse {

    private Long gardenId;

    private String address;
    private double latitude;
    private double longitude;

    private String name;
    private String type;
    private String link;
    private Integer price;

    private String contact;
    private String size;

    private GardenStatus status;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate recruitStartDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate recruitEndDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate useStartDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate useEndDate;

    private String postTitle;
    private String postContent;
    private List<String> images;

    private GardenDetailResponseFacility facility;

    public static GardenDetailResponse of(Garden garden) {
        return GardenDetailResponse.builder()
                .gardenId(garden.getGardenId())

                .address(garden.getAddress())
                .latitude(garden.getLatitude())
                .longitude(garden.getLongitude())

                .name(garden.getName())
                .type(garden.getType().toString())
                .link(garden.getLink())
                .price(Integer.valueOf(garden.getPrice()))
                .contact(garden.getContact())
                .size(garden.getSize())
                .status(garden.getStatus())

                .recruitStartDate(garden.getRecruitStartDate() == null ? null : LocalDate.from(garden.getRecruitStartDate()))
                .recruitEndDate(garden.getRecruitEndDate() == null ? null : LocalDate.from(garden.getRecruitEndDate()))
                .useStartDate(garden.getUseStartDate() == null ? null : LocalDate.from(garden.getUseStartDate()))
                .useEndDate(garden.getUseEndDate() == null ? null : LocalDate.from(garden.getUseEndDate()))

                .images(garden.getImages().stream().map(GardenImage::getUrl).collect(Collectors.toList()))

                .facility(GardenDetailResponseFacility.builder()
                        .toilet(garden.getToilet() != null && garden.getToilet())
                        .waterway(garden.getWaterway() != null && garden.getWaterway())
                        .equipment(garden.getEquipment() != null && garden.getEquipment())
                        .build()
                )

                .build();
    }

}

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
class GardenDetailResponseFacility {
    private boolean toilet;
    private boolean waterway;
    private boolean equipment;
}