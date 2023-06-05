package com.everyonegarden.garden.garden.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ImageUploadSuccessResponse {

    private String id;
    private String imageUrl;

}