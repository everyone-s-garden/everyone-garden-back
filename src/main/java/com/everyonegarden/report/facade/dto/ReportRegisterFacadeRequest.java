package com.everyonegarden.report.facade.dto;

import com.everyonegarden.report.entity.ReportItem;

public record ReportRegisterFacadeRequest (
        ReportItem item,
        String content,
        Long postId
){
}
