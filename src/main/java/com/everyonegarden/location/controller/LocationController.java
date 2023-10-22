package com.everyonegarden.location.controller;

import com.everyonegarden.location.controller.dto.LocationSearchApiRequest;
import com.everyonegarden.location.controller.dto.LocationSearchApiResponses;
import com.everyonegarden.location.controller.mapper.LocationDtoApiMapper;
import com.everyonegarden.location.service.LocationService;

import com.everyonegarden.location.service.dto.LocationSearchResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LocationController {

    private final LocationService locationService;
    private final LocationDtoApiMapper locationDtoApiMapper;

    public LocationController(LocationService locationService, LocationDtoApiMapper locationDtoApiMapper) {
        this.locationService = locationService;
        this.locationDtoApiMapper = locationDtoApiMapper;
    }

    @GetMapping(
            value = "/v1/location",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LocationSearchApiResponses> LocationRequest(@RequestParam @Valid LocationSearchApiRequest request) {
        LocationSearchResponses locationSearchResponses = locationService.autoCompleteLocation(locationDtoApiMapper.toLocationSearchRequest(request));

        return ResponseEntity.ok().body(locationDtoApiMapper.toLocationSearchApiResponses(locationSearchResponses));
    }

}
