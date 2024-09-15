package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Mesa;

import java.util.List;
import java.util.Optional;

public interface MesaGateway {

    Mesa create(Mesa mesa);

    Optional<Mesa> findById(Long id);

    List<Mesa> findByStatus(String status);

    List<Mesa> findByRestauranteId(Long restauranteId);

    void delete(Long id);



}
