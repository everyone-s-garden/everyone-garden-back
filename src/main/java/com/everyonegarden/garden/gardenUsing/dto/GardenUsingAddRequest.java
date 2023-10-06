package com.everyonegarden.garden.gardenUsing.dto;

import com.everyonegarden.common.DateService;
import com.everyonegarden.garden.gardenUsing.GardenUsing;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
