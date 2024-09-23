package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdRestauranteUseCase;

import java.util.Objects;

public class FindFeedBackByIdRestauranteUseCaseImpl implements FindFeedBackByIdRestauranteUseCase {

    private final FeedBackGateway feedBackGateway;

    public FindFeedBackByIdRestauranteUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public FeedBack execute(Long idRestaurante) {
        if (Objects.isNull(idRestaurante))
            throw ValidationException.of("id restaurante", "cannot be null");
        return feedBackGateway.findByIdRestaurante(idRestaurante)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Feedback with id restaurante %s", idRestaurante))
                );
    }
}
