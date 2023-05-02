package com.everyonegarden.user.entity;

import com.everyonegarden.garden.entity.Post;
import com.everyonegarden.user.enunerate.UserProvider;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserProvider userProvider;

    @Column
    private String name;

    @Column
    private String socialId;
    @Column
    private String nickname;

    @Column
    private String roleType;

    @Column
    private int reportScore;


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    public void updateNickname(String name){
        this.name=name;
    }








}
