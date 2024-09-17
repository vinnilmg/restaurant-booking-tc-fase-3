package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBackDomain;

import java.util.List;
import java.util.Optional;

public interface FeedBackGateway {

    FeedBackDomain create(FeedBackDomain feedback);

    List<FeedBackDomain> findAll();

    List<FeedBackDomain> findAllByNomeCliente(String nomeCliente);

    Optional<FeedBackDomain> findById(Long id);

    Optional<FeedBackDomain> findByIdRestaurante(Long id);

    void delete(Long id);
}
