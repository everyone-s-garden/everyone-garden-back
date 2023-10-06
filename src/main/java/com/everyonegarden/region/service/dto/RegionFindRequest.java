package com.everyonegarden.region.service.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class RegionFindRequest {

    private String nx ;
    private String ny ;
    private String regionId;
    private String regionName;

    public RegionFindRequest(String nx, String ny, String regionId, String regionName) {
        this.nx = nx;
        this.ny = ny;
        this.regionId = regionId;
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionFindRequest request = (RegionFindRequest) o;
        return Objects.equals(nx, request.nx) && Objects.equals(ny, request.ny) && Objects.equals(regionId, request.regionId) && Objects.equals(regionName, request.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nx, ny, regionId, regionName);
    }
}
