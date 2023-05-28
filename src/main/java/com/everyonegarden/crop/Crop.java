package com.everyonegarden.crop;

import com.everyonegarden.crop.cropMonth.CropMonth;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Entity @Table
public class Crop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="crop_id")
    private Long cropId;

    private String name;
    private String description;
    private String link;
    private String image;

    @OneToMany(mappedBy = "crop")
    private List<CropMonth> raise_months = new ArrayList<>();

}
