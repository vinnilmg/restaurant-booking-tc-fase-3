package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;

import java.util.List;
import java.util.Optional;

public interface MesaGateway {

    Mesa create(Mesa mesa);

    Optional<Mesa> findById(Long id);

    Optional<Mesa> findById(Long id, Integer numeroMesa);

    List<Mesa> findByStatus(StatusMesaEnum status);

    void deleteByRestaurantIdAndNumeroMesa(Long restauranteId, Integer numeroMesa);

    Optional<Mesa> findByRestauranteIdAndNumeroDaMesa(Long idRestaurante, Integer numeroMesa );

    List<Mesa> findByRestauranteId(Long idRestaurante);
}
