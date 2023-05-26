package com.everyonegarden.garden.gardenLike.model;

import com.everyonegarden.garden.Garden;
import com.everyonegarden.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@IdClass(GardenLikePk.class)
@Entity @Table
public class GardenLike {

    @Id
    private Long memberId;

    @Id
    private Long gardenId;

    @ManyToOne
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "gardenId", insertable = false, updatable = false)
    private Garden garden;

}