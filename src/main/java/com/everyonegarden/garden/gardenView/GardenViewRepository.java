package com.everyonegarden.garden.gardenView;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GardenViewRepository extends JpaRepository<GardenView, Long> {

    @Query("select g from GardenView g where g.member.id = ?1")
    List<GardenView> findByMemberId(Long memberId, Pageable pageable);

}
