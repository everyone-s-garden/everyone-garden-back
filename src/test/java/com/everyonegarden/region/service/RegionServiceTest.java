package com.everyonegarden.region.service;

import com.everyonegarden.Fixtures;
import com.everyonegarden.region.entity.Region;
import com.everyonegarden.region.repository.RegionRepository;

import com.everyonegarden.region.service.dto.RegionFindAllRequests;
import com.everyonegarden.region.service.dto.RegionFindRequest;
import com.everyonegarden.region.service.mapper.RegionDtoMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionDtoMapper regionDtoMapper;

    private Region seoul;
    private Region gangwon;

    @BeforeEach
    void setUp() {
        dataSetup();
    }

    @AfterEach
    void tearDown() {
        regionRepository.deleteAll();
    }

    @Test
    @DisplayName("전체 지역명을 검색했을 때 저장한 지역을 그대로 출력한다.")
    void findAllBy_savedRegions_contains() {
        //given
        RegionFindRequest requestSeoul = regionDtoMapper.toRegionFindRequest(seoul);
        RegionFindRequest requestGangwon = regionDtoMapper.toRegionFindRequest(gangwon);

        //when
        RegionFindAllRequests allBy = regionService.findAllBy();

        //then
        assertThat(allBy.getRegionFindRequests()).containsOnly(requestSeoul,requestGangwon);
    }

    @Test
    @DisplayName("위도와 경도를 지역을 검색하면 올바은 지역명과 지역 코드를 반환한다.")
    void findRegion_withLatitudeAndLongitude_equalsSaved() {
        // given
        String lat = "37.74913611";
        String lng = "128.8784972";

        //when
        RegionFindRequest regionFindRequest = regionService.findRegion(lng, lat);

        //then
        assertThat(regionFindRequest.getRegionName()).isEqualTo(gangwon.makeRegionNameResponse());
        assertThat(regionFindRequest.getNx()).isEqualTo(gangwon.getNx());
        assertThat(regionFindRequest.getNy()).isEqualTo(gangwon.getNy());
    }

    void dataSetup() {
        Region inputSeoul = Fixtures.seoulRegion();
        Region InputGangwon = Fixtures.gangwonRegion();

        seoul = regionRepository.save(inputSeoul);
        gangwon = regionRepository.save(InputGangwon);
    }

}
