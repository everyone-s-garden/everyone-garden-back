package com.everyonegarden.report.controller.dto;

import com.everyonegarden.global.validation.EnumValue;
import com.everyonegarden.report.entity.ReportItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReportRegisterApiRequest(
        @EnumValue(enumClass = ReportItem.class)
        @NotBlank
        String item,
        String content,
        @NotNull
        Long postId
) {
}

