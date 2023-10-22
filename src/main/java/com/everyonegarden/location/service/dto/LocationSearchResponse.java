package com.everyonegarden.location.service.dto;

public record LocationSearchResponse(
        String position,
        Double latitude,
        Double longitude
) {
}
