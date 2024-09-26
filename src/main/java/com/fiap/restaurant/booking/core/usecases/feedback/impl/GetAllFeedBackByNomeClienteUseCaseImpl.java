package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBack;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.GetAllFeedBackByNomeClienteUseCase;

import java.util.List;

public class GetAllFeedBackByNomeClienteUseCaseImpl implements GetAllFeedBackByNomeClienteUseCase {


    private final FeedBackGateway feedBackGateway;

    public GetAllFeedBackByNomeClienteUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public List<FeedBack> execute(String nomeCliente) {
        if (nomeCliente.isEmpty())
            throw ValidationException.of("nomeCliente", "cannot be empty");

        var feedbackList = feedBackGateway.findAllByNomeCliente(nomeCliente);

        if (feedbackList.isEmpty())
            throw NotFoundException.of(String.format("feedbacks by nome cliente %s", nomeCliente));

        return feedbackList;
    }
}
