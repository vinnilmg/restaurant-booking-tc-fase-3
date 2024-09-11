package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;

import java.util.List;

@FunctionalInterface
public interface FindByIdFeedBackUseCase {

    FeedBackDomain execute(Long id);
}
