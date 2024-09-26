package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.DeleteFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;

public class DeleteFeedBackUseCaseImpl implements DeleteFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;
    private final FindFeedBackByIdUseCase findByIdFeedBackUseCase;

    public DeleteFeedBackUseCaseImpl(FeedBackGateway feedBackGateway, FindFeedBackByIdUseCase findByIdFeedBackUseCase) {
        this.feedBackGateway = feedBackGateway;
        this.findByIdFeedBackUseCase = findByIdFeedBackUseCase;
    }


    @Override
    public MessageResponse execute(Long id) {
        findByIdFeedBackUseCase.execute(id);
        feedBackGateway.delete(id);
        return MessageResponse.builder()
                .message(String.format("Feedback by id %s was deleted by success.", id))
                .build();
    }
}
