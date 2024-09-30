package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;

@FunctionalInterface
public interface CreateReservaUseCase {
    Reserva execute(Long restauranteId, Long mesaId, Reserva reserva);
}
