package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.FindConfirmedReservasUseCase;

import java.util.List;

public class FindConfirmedReservasUseCaseImpl implements FindConfirmedReservasUseCase {
    private final ReservaGateway reservaGateway;

    public FindConfirmedReservasUseCaseImpl(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @Override
    public List<Reserva> execute() {
        return reservaGateway.findByStatus(StatusReservaEnum.CONFIRMADA.name());
    }
}
