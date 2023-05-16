package com.everyonegarden.gardenView;

import com.everyonegarden.garden.model.Garden;
import com.everyonegarden.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@EntityListeners(AuditingEntityListener.class)
@Entity @Table
public class GardenView {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenViewId;

    @ManyToOne
    @JoinColumn(name = "garden_id", referencedColumnName = "garden_id")
    private Garden garden;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;


    @CreatedDate
    private LocalDateTime createdDate;

}