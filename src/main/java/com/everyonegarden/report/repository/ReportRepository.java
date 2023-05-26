package com.everyonegarden.report.repository;

import com.everyonegarden.report.entity.Report;
import com.everyonegarden.weather.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ReportRepository extends JpaRepository<Report,Long> {

    Optional<Region> findByPostIdAndReporterId(String postId,String reporterId);

}
