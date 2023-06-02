package com.everyonegarden.crop.cropMonth;

import com.everyonegarden.crop.Crop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Entity @Table(uniqueConstraints = {@UniqueConstraint(name = "crop_month_unique", columnNames = {"months", "crop_id"})})
public class CropMonth {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_month_id")
    private Long cropMonthId;

    @Min(1) @Max(12)
    @Column(nullable = false)
    private Integer months;

    @ManyToOne
    @JoinColumn(name = "crop_id", referencedColumnName = "crop_id")
    private Crop crop;

}
