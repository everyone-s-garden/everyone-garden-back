package com.everyonegarden.location.controller;


import com.everyonegarden.common.dto.ApiResponse;
import com.everyonegarden.location.dto.LocationResponse;
import com.everyonegarden.location.service.AutoCompleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LocationController {

    private final AutoCompleteService autoCompleteService;
    private final Pageable pageable = PageRequest.of(0, 5);

    @GetMapping(value = "/v1/location")
    public List<LocationResponse> LocationRequest(@RequestParam String address) {
        String blankRemovedAddress = address.replace(" ","");

        return autoCompleteService.autoCompleteLocation(blankRemovedAddress,pageable);
    }
}