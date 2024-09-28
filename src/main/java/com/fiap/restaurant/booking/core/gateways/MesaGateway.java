package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.MesaDomain;

import java.util.List;
import java.util.Optional;

public interface MesaGateway {

    MesaDomain create(MesaDomain mesa);

    Optional<MesaDomain> findById(Long id, Integer numeroMesa);

//    Optional<Mesa> findByIdRestauranteAndNumeroMesa(Long idRestaurante, Integer numeroMesa);

    List<MesaDomain> findByStatus(String status);

    void delete(Long id);
}
