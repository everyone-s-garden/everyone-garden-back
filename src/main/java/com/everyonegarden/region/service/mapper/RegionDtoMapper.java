package com.everyonegarden.region.service.mapper;

import com.everyonegarden.region.entity.Region;
import com.everyonegarden.region.service.dto.RegionFindAllRequests;
import com.everyonegarden.region.service.dto.RegionFindRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegionDtoMapper {


    public RegionFindRequest toRegionFindRequest(Region region) {
        return new RegionFindRequest(
                region.getNx(),
                region.getNy(),
                region.getRegionId(),
                region.makeRegionNameResponse()
        );
    }

    public RegionFindAllRequests toRegionFindAllRequests(List<Region> regions) {
        return new RegionFindAllRequests(
                regions.stream()
                        .map(this::toRegionFindRequest)
                        .collect(Collectors.toList())
        );
    }

}
