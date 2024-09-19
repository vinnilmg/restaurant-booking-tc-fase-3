package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;

public class CreateFeedBackUseCaseImpl implements CreateFeedBackUseCase {
    private final FeedBackGateway feedBackGateway;

    public CreateFeedBackUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public FeedBackDomain execute(final FeedBackDomain feedBackDomain) {
        return feedBackGateway.create(feedBackDomain);
    }
}
