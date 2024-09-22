package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.FeedBack;

import java.util.List;
import java.util.Optional;

public interface FeedBackGateway {

    FeedBack create(FeedBack feedback);

    List<FeedBack> findAll();

    List<FeedBack> findAllByNomeCliente(String nomeCliente);

    Optional<FeedBack> findById(Long id);

    Optional<FeedBack> findByIdRestaurante(Long id);

    void delete(Long id);
}
