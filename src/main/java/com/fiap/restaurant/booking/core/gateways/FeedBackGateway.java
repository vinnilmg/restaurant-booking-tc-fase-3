package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;

import java.util.List;

public interface FeedBackGateway {

    FeedBackDomain create(FeedBackDomain feedBackModel);

    List<FeedBackDomain> findAll();

    List<FeedBackDomain> findAllByNomeCliente(String nomeCliente);
}
