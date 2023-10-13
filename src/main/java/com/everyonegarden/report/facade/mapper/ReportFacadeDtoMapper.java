package com.everyonegarden.report.facade.mapper;

import com.everyonegarden.report.controller.dto.ReportRegisterApiResponse;
import com.everyonegarden.report.facade.dto.ReportRegisterFacadeRequest;
import com.everyonegarden.report.service.dto.ReportRegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportFacadeDtoMapper {

    public ReportRegisterApiResponse toReportRegisterApiResponse(boolean state) {
        return new ReportRegisterApiResponse(state);
    }

    public ReportRegisterRequest toReportRegisterRequest(ReportRegisterFacadeRequest request) {
        return new ReportRegisterRequest(
                request.item(),
                request.content(),
                request.postId()
        );
    }

}
