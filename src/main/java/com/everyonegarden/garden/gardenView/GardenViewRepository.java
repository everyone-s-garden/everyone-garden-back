package com.everyonegarden.garden.gardenView;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GardenViewRepository extends JpaRepository<GardenView, Long> {

    @Query("SELECT g FROM GardenView g WHERE " +
            "g.createdDate IN (SELECT MAX(gv.createdDate) FROM GardenView gv WHERE gv.member.id = ?1 " +
            "AND gv.garden.gardenId = g.garden.gardenId) " +
            "AND g.member.id = ?1")
    List<GardenView> findByMemberId(Long memberId, Pageable pageable);

}
