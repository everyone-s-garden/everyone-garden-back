package com.everyonegarden.report.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long reporterId;

    @Column
    private String contents;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReportItem item;

    public Report(Long postId, Long reporterId, String contents, ReportItem item) {
        this.postId = postId;
        this.reporterId = reporterId;
        this.contents = contents;
        this.item = item;
    }


}
