package com.everyonegarden.garden.gardenUsing.dto;

import com.everyonegarden.garden.gardenUsing.GardenUsing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GardenUsingEditRequest {

    private String name;
    private String image;
    private String address;
    private Double latitude;
    private Double longitude;

    private String useStartDate;
    private String useEndDate;

    public GardenUsing toEntity(Long gardenUsingId, Long memberId) {
        List<Integer> useStartDateSplit = Arrays.stream(useStartDate.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> useEndDateSplit = Arrays.stream(useEndDate.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return GardenUsing.builder()
                .id(gardenUsingId)
                .memberId(memberId)
                .name(name)
                .image(image)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .useStartDate(LocalDate.of(
                        useStartDateSplit.get(0),
                        useStartDateSplit.get(1),
                        useStartDateSplit.get(2)
                ))
                .useEndDate(LocalDate.of(
                                useEndDateSplit.get(0),
                                useEndDateSplit.get(1),
                                useEndDateSplit.get(2)
                        )
                )

                .build();
    }

}
