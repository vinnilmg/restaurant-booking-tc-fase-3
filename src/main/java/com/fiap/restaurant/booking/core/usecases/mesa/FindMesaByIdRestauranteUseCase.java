package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.MesaDomain;

public interface FindMesaByIdRestauranteUseCase {

    MesaDomain execute(Long idDoRestaurante);
}
