package com.everyonegarden.garden.garden.dto;

import com.everyonegarden.common.DateService;
import com.everyonegarden.common.exception.BadRequestException;
import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenStatus;
import com.everyonegarden.garden.garden.GardenType;
import com.everyonegarden.garden.gardenImage.GardenImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenEditRequest {

    private String address;
    private Double latitude;
    private Double longitude;

    private String name;
    private String link;

    private String price;
    private String size;
    private String contact;
    private String content;

    private List<String> images;

    private String recruitStartDate;
    private String recruitEndDate;
    private String useStartDate;
    private String useEndDate;

    private GardenEditRequestFacility facility;

    private String type;
    private String status;

    public Garden toEntity(Long gardenId) {
        return Garden.builder()
                .gardenId(gardenId)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .name(name)
                .link(link)

                .price(price)
                .size(size)
                .contact(contact)
                .content(content)

                .toilet(getToilet())
                .waterway(getWaterway())
                .equipment(getEquipment())

                .recruitStartDate(DateService.getDateTimeFromString(recruitStartDate))
                .recruitEndDate(DateService.getDateTimeFromString(recruitEndDate))
                .useStartDate(DateService.getDateTimeFromString(useStartDate))
                .useEndDate(DateService.getDateTimeFromString(useEndDate))

                .type(getGardenType(type))
                .status(getGardenStatus(status))

                .images(images == null ? null : images.stream()
                                .map(image -> GardenImage.builder()
                                        .url(image)
                                        .garden(Garden.builder().gardenId(gardenId).build())
                                        .build())
                                .collect(Collectors.toList())
                )

                .build();
    }

    private GardenType getGardenType(String type) {
        if (type == null) return null;

        GardenType gardenType;

        try {
            gardenType = GardenType.valueOf(type);
        } catch (Exception e) {
            throw new BadRequestException(type + "은 올바른 타입이 아니에요");
        }

        return gardenType;
    }

    private GardenStatus getGardenStatus(String status) {
        if (status == null) return null;

        GardenStatus gardenStatus;

        try {
            gardenStatus = GardenStatus.valueOf(status);
        } catch (Exception e) {
            throw new BadRequestException(status + "는 올바른 타입이 아니에요");
        }

        return gardenStatus;
    }

    private boolean getToilet() {
        if (facility == null) return false;

        return facility.getToilet() != null && facility.getToilet();
    }

    private boolean getWaterway() {
        if (facility == null) return false;

        return facility.getWaterway() != null && facility.getWaterway();
    }

    private boolean getEquipment() {
        if (facility == null) return false;

        return facility.getEquipment() != null && facility.getEquipment();
    }

}

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
class GardenEditRequestFacility {
    private Boolean toilet;
    private Boolean waterway;
    private Boolean equipment;
}
