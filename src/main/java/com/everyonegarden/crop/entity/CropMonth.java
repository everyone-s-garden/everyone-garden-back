package com.everyonegarden.crop.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
public class CropMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cropMonth_id")
    private Long id;

    @Min(1)
    @Max(12)
    @Column(nullable = false)
    private Integer month;


    @ManyToOne
    @JoinColumn(name="CROP_ID")
    private Crop crop;


}
