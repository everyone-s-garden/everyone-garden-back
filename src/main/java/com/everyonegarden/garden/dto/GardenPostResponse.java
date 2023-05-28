package com.everyonegarden.garden.dto;

import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.garden.gardenPost.GardenPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenPostResponse {

    private Long gardenPostId;
    private Long gardenId;

    private String title;
    private String content;

    private GardenResponse garden;

    private List<String> images;

    public static GardenPostResponse of(GardenPost gardenPost) {
        return GardenPostResponse.builder()
                .gardenPostId(gardenPost.getGardenPostId())
                .gardenId(gardenPost.getGarden().getGardenId())

                .title(gardenPost.getTitle())
                .content(gardenPost.getContent())

                .garden(GardenResponse.of(gardenPost.getGarden()))

                .images(gardenPost
                                .getGardenImage().stream()
                                .map(GardenImage::getUrl)
                                .collect(Collectors.toList())
                )

                .build();
    }

}
