package com.fiap.restaurant.booking.core.usecases;

import com.fiap.restaurant.booking.core.domains.Reserva;

@FunctionalInterface
public interface CreateReservaUseCase {
    Reserva execute(Reserva reserva);
}
