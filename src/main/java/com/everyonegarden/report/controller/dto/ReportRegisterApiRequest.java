package com.everyonegarden.report.controller.dto;

import com.everyonegarden.report.entity.ReportItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportRegisterApiRequest(
        @NotBlank ReportItem item,
        String content,
        @NotNull Long postId
) {
}

