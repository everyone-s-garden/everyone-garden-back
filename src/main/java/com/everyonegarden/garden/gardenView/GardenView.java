package com.everyonegarden.garden.gardenView;

import com.everyonegarden.garden.garden.Garden;
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
@Table(name = "GARDEN_VIEW")
@Entity
public class GardenView {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gardenViewId;

    @ManyToOne
    @JoinColumn(name = "garden_id")
    private Garden garden;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    private LocalDateTime createdDate;

}