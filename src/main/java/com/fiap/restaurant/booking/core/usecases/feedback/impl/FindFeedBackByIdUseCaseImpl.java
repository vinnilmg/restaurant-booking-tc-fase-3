package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class FindFeedBackByIdUseCaseImpl implements FindFeedBackByIdUseCase {
    private final FeedBackGateway feedBackGateway;



    @Override
    public FeedBackDomain execute(Long id) {
        if(Objects.isNull(id))
             throw ValidationException.of("id", "cannot be null");
        return feedBackGateway.findById(id)
                .orElseThrow(() ->
                               new NotFoundException(String.format("Feedback with id %s", id)
                    )
                );
    }
}
