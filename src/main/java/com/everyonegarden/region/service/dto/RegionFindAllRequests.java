package com.everyonegarden.region.service.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class RegionFindAllRequests {
    private List<RegionFindRequest> regionFindRequests;

    public RegionFindAllRequests(List<RegionFindRequest> regionFindRequests) {
        this.regionFindRequests = regionFindRequests;
    }
}
