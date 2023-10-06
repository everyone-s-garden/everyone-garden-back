package com.everyonegarden.crop;

import com.everyonegarden.crop.cropMonth.CropMonth;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Table(name = "CROP")
@Entity
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="crop_id")
    private Long cropId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<CropMonth> raise_months = new ArrayList<>();

}
