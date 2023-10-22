package com.everyonegarden.location.controller.dto;

import com.everyonegarden.location.service.dto.LocationSearchResponse;

import java.util.List;

public record LocationSearchApiResponses(
        List<LocationSearchResponse> locationSearchResponses
) {
}
