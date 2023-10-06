package com.everyonegarden.feedback;

import com.everyonegarden.feedback.image.FeedbackImage;
import com.everyonegarden.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@EntityListeners(AuditingEntityListener.class)
@Table(name = "FEEDBACK")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 1500)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToMany(mappedBy = "feedback")
    private List<FeedbackImage> images;

    @CreatedDate
    private LocalDateTime createdDate;

}
