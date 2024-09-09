package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;

@FunctionalInterface
public interface FindReservaByIdUseCase {
    Reserva execute(Long id);
}
