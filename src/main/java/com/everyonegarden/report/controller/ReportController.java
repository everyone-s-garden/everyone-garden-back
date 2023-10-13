package com.everyonegarden.report.controller;

import com.everyonegarden.common.memberId.MemberId;
import com.everyonegarden.report.controller.dto.ReportRegisterApiResponse;
import com.everyonegarden.report.controller.dto.ReportRegisterApiRequest;
import com.everyonegarden.report.controller.mapper.ReportApiMapper;
import com.everyonegarden.report.facade.ReportFacade;
import com.everyonegarden.report.facade.dto.ReportRegisterFacadeRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ReportController {

    private final ReportFacade reportFacade;
    private final ReportApiMapper reportApiMapper;

    public ReportController(ReportFacade reportFacade, ReportApiMapper reportApiMapper) {
        this.reportFacade = reportFacade;
        this.reportApiMapper = reportApiMapper;
    }


    @PostMapping(
            value = "v1/report",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReportRegisterApiResponse> reportPost(@MemberId Long reporterId,
                                                                @RequestBody @Valid ReportRegisterApiRequest reportRegisterApiRequest) {
        ReportRegisterFacadeRequest reportRegisterFacadeRequest = reportApiMapper.toReportRegisterFacadeRequest(reportRegisterApiRequest);
        return ResponseEntity.ok(reportFacade.registerReport(reporterId, reportRegisterFacadeRequest));
    }

}
