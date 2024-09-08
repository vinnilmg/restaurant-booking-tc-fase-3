package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;

import java.util.List;

@FunctionalInterface
public interface ListAllFeedBackUseCase {
    List<FeedBackResponse> execute();
}
