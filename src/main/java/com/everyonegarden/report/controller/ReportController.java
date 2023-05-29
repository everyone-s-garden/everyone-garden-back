package com.everyonegarden.report.controller;

import com.everyonegarden.report.dto.ReportRequestDto;
import com.everyonegarden.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReportController {



    private final ReportService reportService;

    @PostMapping("v1/report")
    public String reportPost(@RequestParam("postId") String postId,
                             @RequestParam("reporterId") String reporterId,
                             @RequestParam("writerId") String writerId,
                             @RequestBody ReportRequestDto reportRequestDto) {

        return reportService.registerReport(postId,reporterId,writerId,reportRequestDto);
    }




}
