package com.everyonegarden.garden.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenAddRequest {

    private List<String> images;
    private String name;
    private String address;
    private String link;
    private Integer price;
    private String size;

}