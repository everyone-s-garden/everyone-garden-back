package com.everyonegarden.garden.gardenUsing.dto;

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
        List<Integer> useStartDateSplit = Arrays.stream(useStartDate.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LocalDate useStartDateFormatted = null;
        try {

            useStartDateFormatted = LocalDate.of(
                    useStartDateSplit.get(0),
                    useStartDateSplit.get(1),
                    useStartDateSplit.get(2)
            );

        } catch (DateTimeException e) {
            throw new BadRequestException("useStartDate의 형식이 올바르지 않아요. 년, 월, 일은 온점(.)으로 구분되야 하고, 생략할 수 없어요. 예) 2023.05.01 (가능), 2023.5.1 (가능), 2023.05 (불가능), 23.05.02 (불가능), 2023.05 (불가능)");
        }

        List<Integer> useEndDateSplit = Arrays.stream(useEndDate.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LocalDate useEndDateFormatted = null;
        try {
            useEndDateFormatted = LocalDate.of(
                    useEndDateSplit.get(0),
                    useEndDateSplit.get(1),
                    useEndDateSplit.get(2)
            );
        } catch (DateTimeException e) {
            throw new BadRequestException("useEndDate의 형식이 올바르지 않아요. 년, 월, 일은 온점(.)으로 구분되야 하고, 생략할 수 없어요. 예) 2023.05.01 (가능), 2023.5.1 (가능), 2023.05 (불가능), 23.05.02 (불가능), 2023.05 (불가능)");
        }

        return GardenUsing.builder()
                .name(name)
                .image(image)
                .memberId(memberId)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .useStartDate(useStartDateFormatted)
                .useEndDate(useEndDateFormatted)

                .build();
    }

}
