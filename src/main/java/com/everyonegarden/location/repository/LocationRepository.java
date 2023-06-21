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
            "WHERE location.fullAddress LIKE %:address% ")
    List<Location> findAllLocation(@Param("address") String address, Pageable pageable);

}
