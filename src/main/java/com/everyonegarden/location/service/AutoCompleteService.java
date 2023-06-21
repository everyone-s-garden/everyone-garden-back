package com.everyonegarden.location.service;

import com.everyonegarden.location.Location;
import com.everyonegarden.location.dto.LocationResponse;
import com.everyonegarden.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AutoCompleteService {

    private final LocationRepository locationRepository;
    public List<LocationResponse> autoCompleteLocation(String fullAddress, Pageable pageable){
        return locationRepository.findAllLocation(fullAddress,pageable).stream()
                .map(LocationResponse::buildLocationResponse)
                .collect(Collectors.toList());
    }
}
