package com.everyonegarden.garden.gardenUsing.dto;

import com.everyonegarden.common.DateService;
import com.everyonegarden.garden.gardenUsing.GardenUsing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenUsingEditRequest {

    private String name;
    private String image;

    private String address;
    private Double latitude;
    private Double longitude;

    private String useStartDate;
    private String useEndDate;

    public GardenUsing toEntity(Long gardenUsingId, Long memberId) {
        return GardenUsing.builder()
                .id(gardenUsingId)
                .memberId(memberId)
                .name(name)
                .image(image)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .useStartDate(DateService.getDateFromString(useStartDate))
                .useEndDate(DateService.getDateFromString(useEndDate))

                .build();
    }

}
