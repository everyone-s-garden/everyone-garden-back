package com.everyonegarden.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Page<Feedback> getAllFeedback(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    public Feedback add(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

}