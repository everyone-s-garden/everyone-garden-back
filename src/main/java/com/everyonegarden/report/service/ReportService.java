package com.everyonegarden.report.service;

import com.everyonegarden.global.error.ErrorCode;
import com.everyonegarden.report.exception.DuplicatedReportException;
import com.everyonegarden.report.repository.ReportRepository;
import com.everyonegarden.report.service.dto.ReportRegisterRequest;
import com.everyonegarden.report.service.mapper.ReportMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReportService {


    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    @Transactional
    public void registerReport(Long reporterId, ReportRegisterRequest request) {
        reportRepository.findByPostIdAndReporterId(request.postId(), reporterId).orElseThrow(() -> new DuplicatedReportException(ErrorCode.DUPLICATED_REPORT));

        reportRepository.save(reportMapper.toReport(reporterId, request));
    }

}

