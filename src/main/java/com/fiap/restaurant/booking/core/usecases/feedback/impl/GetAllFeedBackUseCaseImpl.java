package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackUseCase;

import java.util.List;


public class GetAllFeedBackUseCaseImpl implements GetAllFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;
    public GetAllFeedBackUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public List<FeedBackDomain> execute() {
        return feedBackGateway.findAll();
    }
}
