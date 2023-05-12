package com.everyonegarden.garden.model;


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
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "gardenPost")
    private List<GardenImage> gardenImage = new ArrayList<>();

}