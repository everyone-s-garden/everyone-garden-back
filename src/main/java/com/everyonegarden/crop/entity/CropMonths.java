package com.everyonegarden.crop.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
public class CropMonths {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cropMonths_id")
    private Long id;

    @Min(1)
    @Max(12)
    @Column(nullable = false)
    private Integer months ;


    @ManyToOne
    @JoinColumn(name="crop_id")
    private Crop crop;


}
