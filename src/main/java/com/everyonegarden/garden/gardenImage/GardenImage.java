package com.everyonegarden.garden.gardenImage;

import com.everyonegarden.garden.garden.Garden;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder
@EqualsAndHashCode

@Table(name = "GARDEN_IMAGE")
@Entity
public class GardenImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenPictureId;

    private String url;

    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "garden_id")
    private Garden garden;

}
