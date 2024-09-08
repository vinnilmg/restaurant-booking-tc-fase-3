package com.fiap.restaurant.booking.core.usecases.impl.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.ListAllFeedBackUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


public class ListAllFeedBackUseCaseImpl implements ListAllFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;
    private final FeedBackMapper feedBackMapper;
    public ListAllFeedBackUseCaseImpl(FeedBackMapper feedBackMapper,FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
        this.feedBackMapper = feedBackMapper;
    }

    @Override
    public List<FeedBackResponse> execute() {
        return feedBackGateway.findAll()
                .stream()
                .map(feedBackMapper::toFeedbackResponse)
                .toList();
    }
}
