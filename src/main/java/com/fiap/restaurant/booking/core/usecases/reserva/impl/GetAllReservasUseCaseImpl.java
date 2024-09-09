package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.GetAllReservasUseCase;

import java.util.List;

public class GetAllReservasUseCaseImpl implements GetAllReservasUseCase {
    private final ReservaGateway reservaGateway;

    public GetAllReservasUseCaseImpl(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @Override
    public List<Reserva> execute() {
        return reservaGateway.getAll();
    }
}
