package com.everyonegarden.feedback.dto;

import com.everyonegarden.feedback.Feedback;
import com.everyonegarden.feedback.image.FeedbackImage;
import com.everyonegarden.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
public class FeedbackAddRequest {

    @NotBlank
    private String content;

    private List<String> images;

    public Feedback toEntity(Long memberId) {
        return Feedback.builder()
                .member(Member.builder().id(memberId).build())
                .content(content)
                .images(images.stream()
                        .map(uri -> FeedbackImage.builder()
                                .uri(uri)
                                .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();
    }

}