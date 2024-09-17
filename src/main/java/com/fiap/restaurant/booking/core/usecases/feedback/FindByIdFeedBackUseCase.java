package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;

@FunctionalInterface
public interface FindByIdFeedBackUseCase {
    FeedBackDomain execute(Long id);
}
