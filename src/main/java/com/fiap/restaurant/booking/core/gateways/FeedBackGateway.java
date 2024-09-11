package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;

import java.util.List;
import java.util.Optional;

public interface FeedBackGateway {

    FeedBackDomain create(FeedBackDomain feedBackModel);

    List<FeedBackDomain> findAll();

    List<FeedBackDomain> findAllByNomeCliente(String nomeCliente);

    Optional<FeedBackDomain> findById(Long id);

    String delete(Long id);
}
