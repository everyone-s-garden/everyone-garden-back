package com.everyonegarden.report.controller.mapper;

import com.everyonegarden.report.controller.dto.ReportRegisterApiRequest;
import com.everyonegarden.report.entity.ReportItem;
import com.everyonegarden.report.facade.dto.ReportRegisterFacadeRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportApiMapper {

    public ReportRegisterFacadeRequest toReportRegisterFacadeRequest(ReportRegisterApiRequest request) {
        return new ReportRegisterFacadeRequest(
                ReportItem.find(request.item()),
                request.content(),
                request.postId()
        );
    }

}
