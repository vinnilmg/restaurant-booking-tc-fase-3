package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.infrastructure.controllers.response.MessageResponse;

@FunctionalInterface
public interface DeleteFeedBackUseCase {

    MessageResponse execute(Long id);
}
