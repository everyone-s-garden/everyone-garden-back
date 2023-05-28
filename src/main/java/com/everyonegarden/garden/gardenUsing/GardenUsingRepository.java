package com.everyonegarden.garden.gardenUsing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GardenUsingRepository extends JpaRepository<GardenUsing, Long> {

    @Query("select g from GardenUsing g where g.memberId = ?1")
    List<GardenUsing> findAllByMemberId(Long memberId);

}