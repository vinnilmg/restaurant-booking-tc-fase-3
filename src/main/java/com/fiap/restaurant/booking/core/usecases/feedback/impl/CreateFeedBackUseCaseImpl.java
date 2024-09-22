package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;

import java.util.Objects;

public class CreateFeedBackUseCaseImpl implements CreateFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;

    public CreateFeedBackUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public FeedBack execute(final FeedBack feedBack) {
        if(Objects.isNull(feedBack.getId())) {
            return feedBackGateway.create(feedBack);
        }
        throw new ValidationException("id","has to be null to create a new feedback");
    }
}
