package com.everyonegarden.user.entity;

import com.everyonegarden.garden.entity.Garden;
import com.everyonegarden.garden.entity.Post;
import com.everyonegarden.user.enunerate.UserProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserProvider userProvider;

    private String name;
    private String profileImagePath;//path
    private String socialId;
    private String nickname;

    private String roleType;

    private int reportScore;


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    public void updateNickname(String nickname){
        this.nickname=nickname;
    }

    public void updateProfileImagePath(String profileImagePath){
        this.profileImagePath = profileImagePath;
    }





}
