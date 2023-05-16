package com.everyonegarden.garden.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GardenPostRepository extends JpaRepository<GardenPost, Long> {

    @Query("select g from GardenPost g where g.garden.gardenId = ?1")
    Optional<GardenPost> findByGardenId(Long gardenId);

    @Query("select g from GardenPost g where g.member.id = ?1")
    List<GardenPost> findByMemberId(Long memberId);

}