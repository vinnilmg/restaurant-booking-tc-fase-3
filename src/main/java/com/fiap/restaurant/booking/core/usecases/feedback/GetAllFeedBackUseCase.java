package com.fiap.restaurant.booking.core.usecases.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;

import java.util.List;

@FunctionalInterface
public interface GetAllFeedBackUseCase {

    List<FeedBack> execute();
}
