package com.everyonegarden.garden.gardenImage;


import com.everyonegarden.garden.garden.Garden;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder
@EqualsAndHashCode

@Table @Entity
public class GardenImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenPictureId;

    private String url;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "garden_id")
    private Garden garden;

}