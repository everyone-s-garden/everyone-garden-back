package com.everyonegarden.garden.model;


import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder

@Table
@Entity
public class GardenImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenPictureId;

    private String url;

    @ManyToOne
    @JoinColumn(name="garden_post_id")
    private GardenPost gardenPost;

}