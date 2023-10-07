package com.everyonegarden.report.service.mapper;

import com.everyonegarden.report.entity.Report;
import com.everyonegarden.report.service.dto.ReportRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public Report toReport(Long reporterId, ReportRegisterRequest request) {
        return new Report(
                request.postId(),
                reporterId,
                request.content(),
                request.item());
    }

}
