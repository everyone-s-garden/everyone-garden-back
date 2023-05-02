package com.everyonegarden.garden.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class GardenPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gardenPicture_id")
    private Long id;

    private String gardenPicturePath;


    //연관관계 주인
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;



    public void updateGardenPicturePath(String gardenPicturePath){
        this.gardenPicturePath = gardenPicturePath;
    }


}
