package com.fiap.restaurant.booking.core.usecases.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.CreateReservaUseCase;

public class CreateReservaUseCaseImpl implements CreateReservaUseCase {
    private final ReservaGateway reservaGateway;

    public CreateReservaUseCaseImpl(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @Override
    public Reserva execute(final Reserva reserva) {
        return reservaGateway.create(reserva);
    }
}
