package com.everyonegarden.garden.garden.dto;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenStatus;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenView.GardenView;
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
public class GardenResponse {

    private Long id;

    private String address;
    private Double latitude;
    private Double longitude;

    private String name;
    private String type;
    private String link;
    private String price;

    private String contact;
    private String size;

    private GardenStatus status;

    private Long userId;

    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate recruitStartDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate recruitEndDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate useStartDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate useEndDate;

    private String content;
    private List<String> images;
    private GardenEditRequestFacility facility;

    private boolean liked;

    public GardenResponse updateIsLiked(Boolean liked) {
        this.liked = liked != null && liked;

        return this;
    }

    public static GardenResponse of(Garden garden) {
        Long userId = garden.getMember() == null ?
                null
                :
                garden.getMember().getId();

        return GardenResponse.builder()
                .id(garden.getGardenId())

                .address(garden.getAddress())
                .latitude(garden.getLatitude())
                .longitude(garden.getLongitude())

                .name(garden.getName())
                .type(garden.getType().toString())
                .link(garden.getLink())
                .price(garden.getPrice())
                .contact(garden.getContact())
                .size(garden.getSize())
                .status(garden.getStatus())

                .recruitStartDate(garden.getRecruitStartDate() == null ? null : LocalDate.from(garden.getRecruitStartDate()))
                .recruitEndDate(garden.getRecruitEndDate() == null ? null : LocalDate.from(garden.getRecruitEndDate()))
                .useStartDate(garden.getUseStartDate() == null ? null : LocalDate.from(garden.getUseStartDate()))
                .useEndDate(garden.getUseEndDate() == null ? null : LocalDate.from(garden.getUseEndDate()))

                .images(garden.getImages() == null ? List.of() : garden.getImages().stream().map(GardenImage::getUrl).collect(Collectors.toList()))

                .contact(garden.getContact())
                .content(garden.getContent())

                .liked(false)

                .facility(GardenEditRequestFacility.builder()
                        .toilet(garden.getToilet() != null && garden.getToilet())
                        .waterway(garden.getWaterway() != null && garden.getWaterway())
                        .equipment(garden.getEquipment() != null && garden.getEquipment())
                        .build()
                )

                .userId(userId)
                .build();
    }

    public static GardenResponse of(GardenView gardenView) {
        return GardenResponse.of(gardenView.getGarden());
    }

}

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
class GardenDetailResponseFacility {
    private boolean toilet;
    private boolean waterway;
    private boolean equipment;
}