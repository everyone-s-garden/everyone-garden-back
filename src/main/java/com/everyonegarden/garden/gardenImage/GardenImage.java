package com.everyonegarden.garden.gardenImage;


import com.everyonegarden.garden.garden.Garden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder

@Table @Entity
public class GardenImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenPictureId;

    private String url;

    @ManyToOne @JoinColumn(name="garden_id")
    private Garden garden;

}