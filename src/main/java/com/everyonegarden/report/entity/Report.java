package com.everyonegarden.report.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private Long id;

    @Column
    private Long postId;

    @Column
    private Long reporterId;

    @Column
    private String contents;


}
