package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindByIdFeedBackUseCase;


public class FindByIdFeedBackUseCaseImpl implements FindByIdFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;

    public FindByIdFeedBackUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public FeedBackDomain execute(Long id) {
        return feedBackGateway.findById(id)
                .orElseThrow(() ->  ValidationException.of(
                        "FeedBack",
                        String.format("with id %s not found",id)
                        )
                );
    }
}
