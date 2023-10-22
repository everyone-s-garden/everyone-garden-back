package com.everyonegarden.location.controller.mapper;


import com.everyonegarden.location.controller.dto.LocationSearchApiRequest;
import com.everyonegarden.location.controller.dto.LocationSearchApiResponses;
import com.everyonegarden.location.service.dto.LocationSearchRequest;
import com.everyonegarden.location.service.dto.LocationSearchResponses;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoApiMapper {

    public LocationSearchRequest toLocationSearchRequest(LocationSearchApiRequest request){
        return new LocationSearchRequest(request.eraseBlank());
    }

    public LocationSearchApiResponses toLocationSearchApiResponses(LocationSearchResponses locationSearchResponses) {
        return new LocationSearchApiResponses(locationSearchResponses.locationSearchResponses());
    }
}
