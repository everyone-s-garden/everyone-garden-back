package com.everyonegarden.garden.garden.dto;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.garden.garden.GardenStatus;
import com.everyonegarden.garden.garden.GardenType;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenAddRequest {

    private List<String> images;

    @NotBlank(message = "name은 null이거나 빈칸 (\"\")일 수 없어요")
    private String name;
    private String price;
    private String size;
    private String contact;
    private String status;
    private String content;

    private String address;
    private Double latitude;
    private Double longitude;

    private GardenAddRequestFacility facility;

    private String link;

    public Garden toEntity(Long memberId) {
        return Garden.builder()
                .member(Member.builder().id(memberId).build())

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .name(name)

                .type(GardenType.MANUAL)
                .status(GardenStatus.valueOf(status.toUpperCase()))

                .link(link)
                .price(price)

                .contact(contact)
                .size(size)

                .content(content)

                .toilet(facility.getToilet())
                .waterway(facility.getWaterway())
                .equipment(facility.getEquipment())

                .build();
    }

    public List<GardenImage> getGardenImages(Long gardenId) {
        return images.stream()
                .map(image -> GardenImage.builder()
                        .url(image)
                        .garden(Garden.builder().gardenId(gardenId).build())
                        .build())
                .collect(Collectors.toList());
    }

}

@Getter
@NoArgsConstructor @AllArgsConstructor
class GardenAddRequestFacility {
    private Boolean toilet;
    private Boolean waterway;
    private Boolean equipment;
}