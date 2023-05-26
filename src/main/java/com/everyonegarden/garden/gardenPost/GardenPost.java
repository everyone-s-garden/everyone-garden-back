package com.everyonegarden.garden.gardenPost;


import com.everyonegarden.garden.Garden;
import com.everyonegarden.garden.gardenImage.GardenImage;
import com.everyonegarden.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder

@Table
@Entity
public class GardenPost {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenPostId;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "garden_id")
    private Garden garden;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "gardenPost")
    private List<GardenImage> gardenImage = new ArrayList<>();

}