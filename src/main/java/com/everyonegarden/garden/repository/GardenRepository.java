package com.everyonegarden.garden.repository;

import com.everyonegarden.garden.model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {

    @Query("select g from Garden g where g.type = 'PUBLIC' and " +
            "g.latitude between :xStart and :xEnd and " +
            "g.longitude between :yStart and :yEnd"
    )
    List<Garden> getPublicGardenByCoordinateWithinRange(
            double xStart, double xEnd,
            double yStart, double yEnd
    );

    @Query("select g from Garden g where g.address like %:region% or g.name like %:region%")
    List<Garden> getPublicGardenByRegion(String region);

}
