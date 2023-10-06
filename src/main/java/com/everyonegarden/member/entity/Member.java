package com.everyonegarden.member.entity;

import com.everyonegarden.member.enunerate.MemberProvider;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Embedded
    @Column
    private Name name;

    @Column
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    public String getName() {
        return name.getName();
    }

    @Builder
    public Member(Long id, String email, MemberProvider memberProvider, Name name, String socialId, RoleType roleType) {
        this.id = id;
        this.email = email;
        this.memberProvider = memberProvider;
        this.name = name;
        this.socialId = socialId;
        this.roleType = roleType;
    }

}
