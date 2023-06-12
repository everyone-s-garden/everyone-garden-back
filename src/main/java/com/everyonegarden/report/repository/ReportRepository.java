package com.everyonegarden.report.repository;

import com.everyonegarden.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report,Long> {

    Optional<Report> findByPostIdAndReporterId(Long postId, Long reporterId);

}
