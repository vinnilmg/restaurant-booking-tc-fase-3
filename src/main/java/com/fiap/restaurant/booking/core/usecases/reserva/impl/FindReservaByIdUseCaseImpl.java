package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByIdUseCase;

import static java.util.Objects.isNull;

public class FindReservaByIdUseCaseImpl implements FindReservaByIdUseCase {
    private final ReservaGateway reservaGateway;

    public FindReservaByIdUseCaseImpl(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @Override
    public Reserva execute(final Long id) {
        if (isNull(id) || id < 0) throw ValidationException.of("Reserva Id", "cannot be null or negative");
        return reservaGateway.findById(id)
                .orElseThrow(() -> NotFoundException.of("Reserva"));
    }
}
