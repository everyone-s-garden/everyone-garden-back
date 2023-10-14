package com.everyonegarden.location.service.mapper;

import com.everyonegarden.location.model.Location;
import com.everyonegarden.location.service.dto.LocationSearchResponse;
import com.everyonegarden.location.service.dto.LocationSearchResponses;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationDtoMapper {

    public LocationSearchResponse toLocationSearchResponse(Location location) {
        return new LocationSearchResponse(location.assembleFullAddress(), location.getLatitude(), location.getLongitude());
    }

    public LocationSearchResponses toLocationSearchResponses(List<Location> locations) {
        return new LocationSearchResponses(
                locations.stream()
                        .map(l -> toLocationSearchResponse(l))
                        .toList()
        );
    }

}
