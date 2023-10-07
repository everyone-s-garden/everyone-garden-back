package com.everyonegarden.report.service.dto;

import com.everyonegarden.report.entity.ReportItem;

public record ReportRegisterRequest(
        ReportItem item,
        String content,
        Long postId
) {
}
