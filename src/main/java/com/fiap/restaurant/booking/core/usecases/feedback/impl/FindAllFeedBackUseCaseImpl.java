package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindAllFeedBackUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;

import java.util.List;


public class FindAllFeedBackUseCaseImpl implements FindAllFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;
    public FindAllFeedBackUseCaseImpl( FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public List<FeedBackDomain> execute() {
        return feedBackGateway.findAll();
    }
}
