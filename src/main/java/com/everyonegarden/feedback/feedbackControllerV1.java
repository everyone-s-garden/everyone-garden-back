package com.everyonegarden.feedback;

import com.everyonegarden.common.PageService;
import com.everyonegarden.common.memberId.MemberId;
import com.everyonegarden.feedback.dto.FeedbackAddRequest;
import com.everyonegarden.feedback.dto.FeedbackResponse;
import com.everyonegarden.feedback.image.FeedbackImage;
import com.everyonegarden.feedback.image.FeedbackImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController @RequestMapping("v1/feedback")
public class feedbackControllerV1 {

    private final FeedbackService feedbackService;
    private final FeedbackImageService feedbackImageService;
    private final PageService pageService;

    @GetMapping("all")
    public List<FeedbackResponse> getAllFeedback(@RequestParam(value = "page", required = false) Integer page,
                                                 @RequestParam(value = "size", required = false) Integer size) {
        Pageable pageable = pageService.getPageableSorted(page, size, "createdDate");
        Page<Feedback> feedbackPaged = feedbackService.getAllFeedback(pageable);

        return feedbackPaged.getContent().stream()
                .map(FeedbackResponse::of)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FeedbackResponse addFeedback(@MemberId Long memberId,
                                        @RequestBody @Valid FeedbackAddRequest feedbackAddRequest) {
        Feedback addedFeedback = feedbackService.add(feedbackAddRequest.toEntity(memberId));

        List<FeedbackImage> feedbackImages = feedbackAddRequest.getImages().stream()
                .map(image -> FeedbackImage.builder()
                        .feedback(Feedback.builder().id(addedFeedback.getId()).build())
                        .uri(image)
                        .build())
                .collect(Collectors.toList());

        List<FeedbackImage> addedFeedbackImages = feedbackImageService.addAll(feedbackImages);

        return FeedbackResponse.of(addedFeedback);
    }

}
