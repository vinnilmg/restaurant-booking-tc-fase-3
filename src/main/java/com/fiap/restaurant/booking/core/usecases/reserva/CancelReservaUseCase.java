package com.fiap.restaurant.booking.core.usecases.reserva;

@FunctionalInterface
public interface CancelReservaUseCase {
    void execute(Long id);
}
