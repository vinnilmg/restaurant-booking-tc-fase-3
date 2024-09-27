package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;

@FunctionalInterface
public interface FindFeedBackByIdUseCase {

    FeedBack execute(Long id);
}
