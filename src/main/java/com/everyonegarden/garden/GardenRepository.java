package com.everyonegarden.garden;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {

    @Query("select g from Garden g where g.address like %:query% or g.name like %:query%")
    List<Garden> findAllGardenByQuery(String query, Pageable pageable);

    @Query("select g from Garden g where g.type = 'PUBLIC' and " +
            "g.latitude between :latStart and :latEnd and " +
            "g.longitude between :longStart and :longEnd")
    List<Garden> getPublicGardenByCoordinateWithinRange(
            double latStart, double latEnd,
            double longStart, double longEnd,
            Pageable pageable
    );

    @Query("select g from Garden g where g.type = 'PUBLIC' and (g.address like %:region% or g.name like %:region%)")
    List<Garden> getPublicGardenByRegion(String region, Pageable pageable);


    @Query("select g from Garden g where g.type = 'PRIVATE' and " +
            "g.latitude between :latStart and :latEnd and " +
            "g.longitude between :longStart and :longEnd")
    List<Garden> getPrivateGardenByCoordinateWithinRange(
            double latStart, double latEnd,
            double longStart, double longEnd,
            Pageable pageable
    );

    @Query("select g from Garden g where g.type = 'PUBLIC' and (g.address like %:region% or g.name like %:region%)")
    List<Garden> getPrivateGardenByRegion(String region, Pageable pageable);

    @Query("select g from Garden g where " +
            "g.latitude between :latStart and :latEnd and " +
            "g.longitude between :longStart and :longEnd")
    List<Garden> getAllGardenByCoordinateWithinRange(
            double latStart, double latEnd,
            double longStart, double longEnd,
            Pageable pageable
    );

    @Query("select g from Garden g where g.address like %:region% or g.name like %:region%")
    List<Garden> getAllGardenByRegion(String region, Pageable pageable);

    @Query("select g from Garden g")
    Page<Garden> findAll(Pageable pageable);

}