package com.everyonegarden.report.facade;

import com.everyonegarden.garden.garden.GardenService;
import com.everyonegarden.report.controller.dto.ReportRegisterApiResponse;
import com.everyonegarden.report.facade.dto.ReportRegisterFacadeRequest;
import com.everyonegarden.report.facade.mapper.ReportFacadeDtoMapper;
import com.everyonegarden.report.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportFacade {

    private final GardenService gardenService;
    private final ReportService reportService;
    private final ReportFacadeDtoMapper reportFacadeDtoMapper;


    public ReportFacade(GardenService gardenService, ReportService reportService, ReportFacadeDtoMapper reportFacadeDtoMapper) {
        this.gardenService = gardenService;
        this.reportService = reportService;
        this.reportFacadeDtoMapper = reportFacadeDtoMapper;
    }

    @Transactional
    public ReportRegisterApiResponse registerReport(Long reporterId, ReportRegisterFacadeRequest request){
        reportService.registerReport(reporterId, reportFacadeDtoMapper.toReportRegisterRequest(request));

        int reportScore = request.item().getScore();
        gardenService.sumReportScore(reporterId, reportScore);

        return reportFacadeDtoMapper.toReportRegisterApiResponse(true);
    }

}
