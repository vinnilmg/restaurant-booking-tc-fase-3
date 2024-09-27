package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.MesaDomain;

import java.util.List;
import java.util.Optional;

public interface MesaGateway {

    MesaDomain create(MesaDomain mesa);

    Optional<MesaDomain> findById(Long id);

    List<MesaDomain> findByStatus(String status);

    List<MesaDomain> findByRestauranteId(Long restauranteId);

    void delete(Long id);
}
