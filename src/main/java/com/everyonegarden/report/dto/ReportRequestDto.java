package com.everyonegarden.report.dto;

import lombok.Data;

@Data
public class ReportRequestDto {
    private String item;
    private String content;
    private Long postId;
}

