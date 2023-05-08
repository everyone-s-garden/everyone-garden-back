package com.everyonegarden.garden.model;


import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder

@Table
@Entity
public class GardenPicture {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenPictureId;

    private String gardenPicturePath;

    @ManyToOne
    @JoinColumn(name="garden_post_id")
    private GardenPost gardenPost;

}