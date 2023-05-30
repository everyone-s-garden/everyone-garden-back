package com.everyonegarden.garden.dto;


import com.everyonegarden.garden.Garden;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenPost.GardenPost;
import com.everyonegarden.garden.GardenType;
import com.everyonegarden.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenPostAddRequest {

    private String name;
    private String price;
    private String size;
    private String contact;
    private String link;

    private String address;
    private Double latitude;
    private Double longitude;

    private List<String> images;

    public Garden toGardenEntity() {
        return Garden.builder()
                .type(GardenType.MANUAL)

                .name(name)
                .price(price)
                .size(size)
                .contact(contact)
                .link(link)

                .address(address)
                .latitude(latitude)
                .longitude(longitude)

                .build();
    }

    public GardenPost toGardenPostEntity(Long userId, Long gardenId) {
        return GardenPost.builder()

                .title(name)
                .member(Member.builder().id(userId).build())
                .garden(Garden.builder().gardenId(gardenId).build())

                .build();
    }

    public List<GardenImage> toGardenImageEntityList(Long gardenPostId) {
        return images.stream()
                .map(image -> GardenImage.builder()

                        .url(image)
                        .gardenPost(GardenPost.builder().gardenPostId(gardenPostId).build())

                        .build())
                .collect(Collectors.toList());
    }

}