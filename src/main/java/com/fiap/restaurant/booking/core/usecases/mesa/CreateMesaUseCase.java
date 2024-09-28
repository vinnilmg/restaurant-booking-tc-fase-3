package com.fiap.restaurant.booking.core.usecases.mesa;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.Restaurante;

@FunctionalInterface
public interface CreateMesaUseCase {

    MesaDomain execute(MesaDomain mesaDomain, Long idRestaurante);
}
