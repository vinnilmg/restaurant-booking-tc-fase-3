package com.fiap.restaurant.booking.core.usecases.reserva;

@FunctionalInterface
public interface ConfirmReservaUseCase {
    void execute(Long id);
}
