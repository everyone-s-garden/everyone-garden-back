package com.everyonegarden.feedback.dto;

import com.everyonegarden.feedback.Feedback;
import com.everyonegarden.feedback.image.FeedbackImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class FeedbackResponse {

    private FeedbackResponseMember user;

    private String content;
    private List<String> images;

    public static FeedbackResponse of(Feedback feedback) {
        return FeedbackResponse.builder()
                .user(FeedbackResponseMember.builder()
                        .email(feedback.getMember().getEmail())
                        .name(feedback.getMember().getName())
                        .build())
                .content(feedback.getContent())
                .images(feedback.getImages().stream()
                        .map(FeedbackImage::getUri)
                        .collect(Collectors.toList())
                )
                .build();
    }

}

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
class FeedbackResponseMember {
    private String email;
    private String name;
}
