package com.everyonegarden.weather.repository;


import com.everyonegarden.weather.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

   Optional<Region> findById(Long id);

   Region findByRegionName(String region);

}
