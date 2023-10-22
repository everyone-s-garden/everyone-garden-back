package com.everyonegarden.location.service;

import com.everyonegarden.location.repository.LocationRepository;
import com.everyonegarden.location.service.dto.LocationSearchResponses;
import com.everyonegarden.testutil.Fixtures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        locationRepository.save(Fixtures.인천광역시());
        locationRepository.save(Fixtures.인천광역시서구());
        locationRepository.save(Fixtures.인천광역시서구가좌동());
        locationRepository.save(Fixtures.인천광역시서구석남동());
        locationRepository.save(Fixtures.인천광역시서구신현동());
    }

    @Test
    void autoCompleteLocation() {
        //when
        LocationSearchResponses locationSearchResponses = locationService.autoCompleteLocation(Fixtures.인천Request());

        //then
        assertThat(locationSearchResponses.locationSearchResponses()).containsExactlyInAnyOrderElementsOf( Fixtures.인천LocationSearchResponses());
    }

}
