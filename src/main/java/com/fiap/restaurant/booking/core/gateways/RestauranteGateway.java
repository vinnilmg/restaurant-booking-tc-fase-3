package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteGateway {

    Restaurante create(Restaurante restaurante);

    List<Restaurante> getAll();

    Optional<Restaurante> findById(Long id);

    List<Restaurante> findByNome(String nome);

    List<Restaurante> findByTipoCulinaria(String tipoCulinaria);

    List<Restaurante> findByMediaFeedback(Double mediaFeedback);

    void update(Restaurante restaurante);
}
