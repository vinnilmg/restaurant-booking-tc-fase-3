package com.fiap.restaurant.booking.core.usecases.impl.feedback;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.CreateFeedBackUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.FeedBackMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.FeedBackRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.FeedBackResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component

public class CreateFeedBackUseCaseImpl implements CreateFeedBackUseCase {

    private final FeedBackMapper feedBackMapper;

    private final FeedBackGateway feedBackGateway;

    public CreateFeedBackUseCaseImpl(FeedBackMapper feedBackMapper, FeedBackGateway feedBackGateway) {
        this.feedBackMapper = feedBackMapper;
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public FeedBackResponse execute(final FeedBackRequest feedBackRequest) {
        Optional<FeedBack> feedBack = Optional.of(feedBackMapper.toFeedBackDomain(feedBackRequest));
        return Optional.of(feedBackGateway.create(feedBack)).
                map(feedBackMapper::toFeedbackResponse)
                .orElse(null);

    }
}
