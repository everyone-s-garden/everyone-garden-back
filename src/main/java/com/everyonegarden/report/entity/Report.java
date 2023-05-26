package com.everyonegarden.report.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private String post_id;

    @Column
    private String reporter_id;

    @Column
    private String contents;




}
