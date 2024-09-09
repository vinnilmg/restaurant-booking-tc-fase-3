package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.ConfirmReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByIdUseCase;

public class ConfirmReservaUseCaseImpl implements ConfirmReservaUseCase {
    private final FindReservaByIdUseCase findReservaByIdUseCase;
    private final ReservaGateway reservaGateway;

    public ConfirmReservaUseCaseImpl(FindReservaByIdUseCase findReservaByIdUseCase, ReservaGateway reservaGateway) {
        this.findReservaByIdUseCase = findReservaByIdUseCase;
        this.reservaGateway = reservaGateway;
    }

    @Override
    public void execute(final Long id) {
        final var reserva = findReservaByIdUseCase.execute(id);

        if (reserva.isConfirmed()) {
            throw ValidationException.of("Reserva", "is already confirmed");
        }

        reserva.updateStatus(StatusReservaEnum.CONFIRMADA);
        reservaGateway.update(reserva);
    }
}
