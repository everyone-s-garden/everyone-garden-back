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
public class GardenUsingAddRequest {

    @NotBlank(message = "name은 null이거나 빈칸일 수 없어요")
    private String name;
    private String image;

    private String address;
    private Double latitude;
    private Double longitude;

    @NotBlank(message = "useStartDate은 null이거나 빈칸일 수 없어요")
    private String useStartDate;

    @NotBlank(message = "useEndDate은 null이거나 빈칸일 수 없어요")
    private String useEndDate;

    public GardenUsing toEntity(Long memberId) {
        return GardenUsing.builder()
                .name(name)
                .image(image)
                .memberId(memberId)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .useStartDate(DateService.getDateFromString(useStartDate))
                .useEndDate(DateService.getDateFromString(useEndDate))

                .build();
    }

}
