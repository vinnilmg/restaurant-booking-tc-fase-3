package com.fiap.restaurant.booking.core.usecases;

import com.fiap.restaurant.booking.core.domains.Reserva;

import java.util.List;

@FunctionalInterface
public interface GetAllReservasUseCase {
    List<Reserva> execute();
}
