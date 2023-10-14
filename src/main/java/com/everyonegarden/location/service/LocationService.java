package com.everyonegarden.location.service;

import com.everyonegarden.location.repository.LocationRepository;

import com.everyonegarden.location.service.dto.LocationSearchRequest;
import com.everyonegarden.location.service.dto.LocationSearchResponses;
import com.everyonegarden.location.service.mapper.LocationDtoMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    private static final Pageable pageable = PageRequest.of(0, 5);
    private final LocationRepository locationRepository;
    private final LocationDtoMapper locationDtoMapper;

    public LocationService(LocationRepository locationRepository, LocationDtoMapper locationDtoMapper) {
        this.locationRepository = locationRepository;
        this.locationDtoMapper = locationDtoMapper;
    }

    @Transactional(readOnly = true)
    public LocationSearchResponses autoCompleteLocation(LocationSearchRequest request) {
        return locationDtoMapper.toLocationSearchResponses(
                locationRepository.findAllLocation(
                        request.address()
                        , pageable));
    }

}
