package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Mesa;

import java.util.List;
import java.util.Optional;

public interface MesaGateway {

    Mesa create(Mesa mesa);

    Optional<Mesa> findById(Long id);

    Optional<Mesa> findById(Long id, Integer numeroMesa);

//    Optional<Mesa> findByIdRestauranteAndNumeroMesa(Long idRestaurante, Integer numeroMesa);

    List<Mesa> findByStatus(String status);

    void delete(Long id);
}
