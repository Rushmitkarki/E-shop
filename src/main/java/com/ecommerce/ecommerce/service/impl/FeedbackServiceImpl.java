package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.Feedback;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.FeedbackRepo;
import com.ecommerce.ecommerce.service.FeedbackService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepo feedbackRepo;
    private final UserService userService;

    @Override
    public void saveFeedback(com.ecommerce.ecommerce.dto.FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(feedbackDto.getFeedback());
        User user = userService.getActiveUser().get();
        feedback.setUser(user);
        feedbackRepo.save(feedback);
    }
}
