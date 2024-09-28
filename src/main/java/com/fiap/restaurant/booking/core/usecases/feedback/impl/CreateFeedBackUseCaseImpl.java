package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;

import static java.util.Objects.isNull;

public class CreateFeedBackUseCaseImpl implements CreateFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;
    private final FindRestauranteByIdUseCase findRestauranteByIdUseCase;

    public CreateFeedBackUseCaseImpl(FeedBackGateway feedBackGateway, FindRestauranteByIdUseCase findRestauranteByIdUseCase) {
        this.feedBackGateway = feedBackGateway;
        this.findRestauranteByIdUseCase = findRestauranteByIdUseCase;
    }

    @Override
    public FeedBack execute(final FeedBack feedBack, final Long restauranteId) {
        Restaurante restaurante = findRestauranteByIdUseCase.execute(restauranteId);

        if (isNull(feedBack.getId())) {
            feedBack.setRestaurante(restaurante);
            return feedBackGateway.create(feedBack);
        }

        throw new ValidationException("id", "has to be null to create a new feedback");
    }
}
