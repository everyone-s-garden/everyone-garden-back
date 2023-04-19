package com.everyonegarden.user.entity;

import com.everyonegarden.auth.enumerate.RoleType;
import com.everyonegarden.user.enunerate.UserProvider;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserProvider userProvider;

    private String name;
    private String profileImagePath;//path
    private String socialId;
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    public void updateNickname(String nickname){
        this.name=name;
    }

    public void updateProfileImagePath(String profileImagePath){
        this.profileImagePath = profileImagePath;
    }





}
