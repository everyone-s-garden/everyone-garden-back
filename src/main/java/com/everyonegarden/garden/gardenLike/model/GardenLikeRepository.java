package com.everyonegarden.garden.gardenLike.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GardenLikeRepository extends JpaRepository<GardenLike, Long> {

    @Query("select g from GardenLike g where g.memberId = ?1")
    List<GardenLike> findByMemberId(Long memberId, Pageable pageable);

    @Query("select g from GardenLike g where g.memberId = ?1 and g.gardenId = ?2")
    Optional<GardenLike> findByMemberIdAndGardenId(Long memberId, Long gardenId);

    @Transactional
    @Modifying
    @Query("delete from GardenLike g where g.memberId = ?1 and g.gardenId = ?2")
    void deleteByMemberIdAndGardenId(Long memberId, Long gardenId);

}
