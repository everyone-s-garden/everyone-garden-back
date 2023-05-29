package com.everyonegarden.member.entity;

import com.everyonegarden.member.enunerate.MemberProvider;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberProvider memberProvider;

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

    @Column
    private Boolean permanentSusp=false;

}
