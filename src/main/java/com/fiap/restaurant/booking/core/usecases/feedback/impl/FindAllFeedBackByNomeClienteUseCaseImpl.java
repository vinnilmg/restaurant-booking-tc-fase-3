package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindAllFeedBackByNomeClienteUseCase;

import java.util.List;

public class FindAllFeedBackByNomeClienteUseCaseImpl implements FindAllFeedBackByNomeClienteUseCase {


    private final FeedBackGateway feedBackGateway;

    public FindAllFeedBackByNomeClienteUseCaseImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public List<FeedBackDomain> execute(String nomeCliente) {
        var feedBackDomainList = feedBackGateway.findAllByNomeCliente(nomeCliente);

        if (feedBackDomainList.isEmpty())
            throw NotFoundException.of(String.format("Not found feedbacks by nome cliente %s", nomeCliente));

        return feedBackDomainList;
    }
}
