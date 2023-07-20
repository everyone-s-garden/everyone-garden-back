package com.everyonegarden.garden.gardenLike.model;

import com.everyonegarden.garden.garden.Garden;
import com.everyonegarden.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@IdClass(GardenLikePk.class)
@Table(name = "GARDEN_LIKE")
@Entity
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

    @CreatedDate
    private LocalDateTime createdDate;

}