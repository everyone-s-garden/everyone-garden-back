package com.everyonegarden.garden.gardenUsing.dto;

import com.everyonegarden.common.DateService;
import com.everyonegarden.common.exception.BadRequestException;
import com.everyonegarden.garden.gardenUsing.GardenUsing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
