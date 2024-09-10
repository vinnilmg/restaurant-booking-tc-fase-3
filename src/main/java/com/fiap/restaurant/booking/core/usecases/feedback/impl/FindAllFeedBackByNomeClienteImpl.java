package com.fiap.restaurant.booking.core.usecases.feedback.impl;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;
import com.fiap.restaurant.booking.core.gateways.FeedBackGateway;
import com.fiap.restaurant.booking.core.usecases.feedback.FindAllFeedBackByNomeClienteUseCase;

import java.util.List;

public class FindAllFeedBackByNomeClienteImpl implements FindAllFeedBackByNomeClienteUseCase {


    private final FeedBackGateway feedBackGateway;

    public FindAllFeedBackByNomeClienteImpl(FeedBackGateway feedBackGateway) {
        this.feedBackGateway = feedBackGateway;
    }

    @Override
    public List<FeedBackDomain> execute(String nomeCliente) {
        return feedBackGateway.findAllByNomeCliente(nomeCliente);
    }
}
