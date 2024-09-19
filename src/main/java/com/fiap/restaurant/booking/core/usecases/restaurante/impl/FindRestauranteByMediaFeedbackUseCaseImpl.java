package com.fiap.restaurant.booking.core.usecases.restaurante.impl;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.RestauranteGateway;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByMediaFeedbackUseCase;

import java.util.List;

import static java.util.Objects.isNull;

public class FindRestauranteByMediaFeedbackUseCaseImpl implements FindRestauranteByMediaFeedbackUseCase{
    private final RestauranteGateway restauranteGateway;


    public FindRestauranteByMediaFeedbackUseCaseImpl(RestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public List<Restaurante> execute(final Double mediaFeedback) {
        if (isNull(mediaFeedback) || mediaFeedback < 0)
            throw ValidationException.of("Media Feedback", "cannot be null or negative");
        return restauranteGateway.findByMediaFeedback(mediaFeedback);
    }
}
