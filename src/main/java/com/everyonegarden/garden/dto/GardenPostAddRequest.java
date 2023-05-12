package com.everyonegarden.garden.dto;

import com.everyonegarden.auth.user.entity.User;
import com.everyonegarden.garden.model.Garden;
import com.everyonegarden.garden.model.GardenImage;
import com.everyonegarden.garden.model.GardenPost;
import com.everyonegarden.garden.model.GardenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenPostAddRequest {

    private String title;
    private String content;
    private String contact;

    private GardenAddRequest garden;

    public Garden toGardenEntity() {
        return Garden.builder()

                .type(GardenType.MANUAL)
                .address(garden.getAddress())
                .name(garden.getName())
                .link(garden.getLink())
                .price(garden.getPrice())
                .contact(contact)
                .size(garden.getSize())

                .build();
    }

    public GardenPost toGardenPostEntity(Long userId, Long gardenId) {
        return GardenPost.builder()

                .title(title)
                .content(content)
                .user(User.builder().id(userId).build())

                .build();
    }

    public List<GardenImage> toGardenImageEntityList(Long gardenPostId) {
        return garden.getImages().stream()
                .map(image -> GardenImage.builder()

                        .url(image)
                        .gardenPost(GardenPost.builder().gardenPostId(gardenPostId).build())

                        .build())
                .collect(Collectors.toList());
    }

}