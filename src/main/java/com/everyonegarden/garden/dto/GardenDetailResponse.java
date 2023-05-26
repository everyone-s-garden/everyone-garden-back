package com.everyonegarden.garden.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenDetailResponse {

    private Long gardenId;

    private String address;
    private double latitude;
    private double longitude;

    private String name;
    private String type;
    private String link;
    private Integer price;

    private String contact;
    private String size;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recruitEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate useStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate useEndDate;

    private String postTitle;
    private String postContent;
    private List<String> images;

}