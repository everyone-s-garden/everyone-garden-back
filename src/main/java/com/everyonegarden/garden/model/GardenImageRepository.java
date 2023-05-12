package com.everyonegarden.garden.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GardenImageRepository extends JpaRepository<GardenImage, Long> {

    @Query("select g from GardenImage g where g.gardenPost.gardenPostId = ?1")
    List<GardenImage> findByGardenPostId(Long gardenPostId);

}
