package com.everyonegarden.garden.garden.dto;

import com.everyonegarden.garden.garden.Garden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenEditRequest {

    private String address;

    private String name;
    private String link;

    private Integer price;
    private String size;
    private String contact;

    public Garden toEntity(Long gardenId) {
        return Garden.builder()
                .gardenId(gardenId)

                .address(address)
                .name(name)
                .link(link)

                .price(String.valueOf(price))
                .size(size)
                .contact(contact)

                .build();
    }

}
