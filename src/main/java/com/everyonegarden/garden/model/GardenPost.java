package com.everyonegarden.garden.model;

import com.everyonegarden.auth.user.entity.User;
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
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "gardenPost")
    private List<GardenPicture> gardenPicture = new ArrayList<>();

}