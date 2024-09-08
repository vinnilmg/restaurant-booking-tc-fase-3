package com.fiap.restaurant.booking.core.usecases.impl.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.core.usecases.feedback.ListAllFeedBackUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class ListAllFeedBackUseCaseImpl implements ListAllFeedBackUseCase {

    private final FeedBackGateway feedBackGateway;

    public ListAllFeedBackUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public List<FeedBack> execute() {
        return feedBackGateway.findAll();
    }
}
