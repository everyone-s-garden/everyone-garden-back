package com.everyonegarden.user.entity;

import com.everyonegarden.garden.entity.Garden;
import com.everyonegarden.garden.entity.Post;
import com.everyonegarden.user.enunerate.UserProvider;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserProvider userProvider;

    //private String name;
    private String profileImagePath;//path
    private String socialId;

    private String username;
    private String nickname;
    private String password;

    private String roles;


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    public void updateNickname(String nickname){
        this.nickname=nickname;
    }

    public void updateProfileImagePath(String profileImagePath){
        this.profileImagePath = profileImagePath;
    }

    // ENUM으로 안하고 ,로 해서 구분해서 ROLE을 입력 -> 그걸 파싱!!
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }




}
