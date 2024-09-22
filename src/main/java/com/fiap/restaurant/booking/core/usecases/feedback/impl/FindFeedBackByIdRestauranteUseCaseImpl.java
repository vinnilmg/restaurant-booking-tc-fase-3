package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindFeedBackByIdRestauranteUseCase;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class FindFeedBackByIdRestauranteUseCaseImpl implements FindFeedBackByIdRestauranteUseCase {

    private final FeedBackGateway feedBackGateway;

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
