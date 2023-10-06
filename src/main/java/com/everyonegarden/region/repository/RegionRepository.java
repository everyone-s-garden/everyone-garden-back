package com.everyonegarden.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.everyonegarden.region.entity.Region;
import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

   Optional<Region> findById(Long id);

   Region findByRegionName(String region);

   List<Region> findAllBy();

}
