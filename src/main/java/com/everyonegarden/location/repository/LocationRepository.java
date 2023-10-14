package com.everyonegarden.location.repository;

import com.everyonegarden.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM location WHERE MATCH(location.fullAddress) AGAINST(:address IN NATURAL LANGUAGE MODE ) LIMIT :pageSize OFFSET :pageNumber", nativeQuery = true)
    List<Location> findAllLocation(@Param("address") String address, @Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);

}
