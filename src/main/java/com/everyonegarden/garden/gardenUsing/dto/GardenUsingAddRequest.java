package com.everyonegarden.garden.gardenUsing.dto;

import com.everyonegarden.garden.gardenUsing.GardenUsing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenUsingAddRequest {

    private String name;

    private String address;
    private Double latitude;
    private Double longitude;

    private String useStartDate;
    private String useEndDate;

    private String image;

    public GardenUsing toEntity(Long memberId) {
        List<Integer> useStartDateSplit = Arrays.stream(useStartDate.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> useEndDateSplit = Arrays.stream(useEndDate.split("-"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return GardenUsing.builder()
                .name(name)
                .image(image)
                .memberId(memberId)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .useStartDate(LocalDate.of(useStartDateSplit.get(0), useStartDateSplit.get(1), useStartDateSplit.get(2)))
                .useEndDate(LocalDate.of(useEndDateSplit.get(0), useEndDateSplit.get(1), useEndDateSplit.get(2)))

                .build();
    }

}
