package com.everyonegarden.location.repository;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.location.Location;
import com.everyonegarden.location.dto.LocationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT location FROM Location location " +
            "WHERE location.level1 LIKE %:level1% " +
            "OR location.level2 LIKE %:level2% " +
            "OR location.level3 LIKE %:level3% " +
            "OR location.level4 LIKE %:level4% ")
    List<Location> findAllLocationByLevel(@Param("level1") String level1,
                                          @Param("level2") String level2,
                                          @Param("level3") String level3,
                                          @Param("level4") String level4,
                                          Pageable pageable);

}
