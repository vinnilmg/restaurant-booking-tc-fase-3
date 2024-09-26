package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;

@FunctionalInterface
public interface FindFeedBackByIdRestauranteUseCase {

    FeedBack execute(Long idRestaurante);
}
