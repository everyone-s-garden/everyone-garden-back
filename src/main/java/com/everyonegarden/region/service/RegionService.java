package com.everyonegarden.region.service;

import com.everyonegarden.region.repository.RegionRepository;
import com.everyonegarden.region.infra.ReverseGeoFetcher;
import com.everyonegarden.region.service.dto.RegionFindAllRequests;
import com.everyonegarden.region.service.dto.RegionFindRequest;
import com.everyonegarden.region.service.mapper.RegionDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;
    private final ReverseGeoFetcher reverseGeoFetcher;
    private final RegionDtoMapper regionDtoMapper;

    public RegionService(RegionRepository regionRepository, ReverseGeoFetcher reverseGeoFetcher, RegionDtoMapper regionDtoMapper) {
        this.regionRepository = regionRepository;
        this.reverseGeoFetcher = reverseGeoFetcher;
        this.regionDtoMapper = regionDtoMapper;
    }

    @Transactional(readOnly = true)
    public RegionFindAllRequests findAllBy() {
        return regionDtoMapper.toRegionFindAllRequests(regionRepository.findAllBy());
    }

    @Transactional(readOnly = true)
    public RegionFindRequest findRegion(String lng, String lat) {
        String regionName = reverseGeoFetcher.getRegionName(lng, lat);

        return regionDtoMapper.toRegionFindRequest(regionRepository.findByRegionName(regionName));
    }

}
