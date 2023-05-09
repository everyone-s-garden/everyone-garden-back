package com.everyonegarden.garden.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ImageUploadSuccessResponse {

    private UUID id;
    private String imageUrl;

}