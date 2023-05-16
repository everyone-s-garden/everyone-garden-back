package com.everyonegarden.crop.entity;

import com.everyonegarden.garden.model.GardenPost;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="crop_id")
    private Long id;

    private String name;
    private String howto;

    @OneToMany(mappedBy = "crop")
    private List<CropMonths> raise_months = new ArrayList<>();

}
