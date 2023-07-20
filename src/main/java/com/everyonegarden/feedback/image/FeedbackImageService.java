package com.everyonegarden.feedback.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackImageService {

    private final FeedbackImageRepository feedbackImageRepository;

    public List<FeedbackImage> addAll(List<FeedbackImage> feedbackImages) {
        return feedbackImageRepository.saveAll(feedbackImages);
    }

}