package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;

@FunctionalInterface
public interface CreateFeedBackUseCase {
    FeedBackResponse execute(FeedBackRequest feedBack);
}
