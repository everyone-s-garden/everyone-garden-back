package com.everyonegarden.feedback.image;

import com.everyonegarden.feedback.Feedback;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder

@Table(name = "FEEDBACK_IMAGE")
@Entity
public class FeedbackImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uri")
    private String uri;

    @ManyToOne @JoinColumn(name = "feedback_id", referencedColumnName = "id")
    private Feedback feedback;

}