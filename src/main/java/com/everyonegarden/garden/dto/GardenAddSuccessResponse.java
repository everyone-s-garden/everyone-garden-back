package com.everyonegarden.garden.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GardenAddSuccessResponse {

    private GardenResponse garden;

}