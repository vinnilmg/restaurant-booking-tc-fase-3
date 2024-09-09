package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.CancelReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByIdUseCase;

public class CancelReservaUseCaseImpl implements CancelReservaUseCase {
    private final FindReservaByIdUseCase findReservaByIdUseCase;
    private final ReservaGateway reservaGateway;

    public CancelReservaUseCaseImpl(FindReservaByIdUseCase findReservaByIdUseCase, ReservaGateway reservaGateway) {
        this.findReservaByIdUseCase = findReservaByIdUseCase;
        this.reservaGateway = reservaGateway;
    }

    @Override
    public void execute(final Long id) {
        final var reserva = findReservaByIdUseCase.execute(id);

        if (reserva.isCanceled()) {
            throw ValidationException.of("Reserva", "is already canceled");
        }

        reserva.updateStatus(StatusReservaEnum.CANCELADA);
        reservaGateway.update(reserva);
    }
}
