package com.everyonegarden.location.service;

import com.everyonegarden.location.model.Location;
import com.everyonegarden.location.repository.LocationRepository;
import com.everyonegarden.location.service.dto.LocationSearchRequest;
import com.everyonegarden.location.service.dto.LocationSearchResponses;
import com.everyonegarden.location.service.mapper.LocationDtoMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    private static final int PAGE_SIZE = 5;
    private static final int PAGE_NUMBER = 0;
    private final LocationRepository locationRepository;
    private final LocationDtoMapper locationDtoMapper;

    public LocationService(LocationRepository locationRepository, LocationDtoMapper locationDtoMapper) {
        this.locationRepository = locationRepository;
        this.locationDtoMapper = locationDtoMapper;
    }

    @Transactional(readOnly = true)
    public LocationSearchResponses autoCompleteLocation(LocationSearchRequest request) {
        List<Location> allLocation = locationRepository.findAllLocation(
                request.address()
                ,PAGE_SIZE
                ,PAGE_NUMBER );

        return locationDtoMapper.toLocationSearchResponses(
                allLocation);
    }

}
