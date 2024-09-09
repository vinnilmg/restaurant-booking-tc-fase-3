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
        /*
         * TODO:
         *  - Verificar se existe uma reserva em andamento para o cliente
         *  - Verificar se o restaurante + a mesa existem
         *  - Verifica se o restaurante está em aberto no período solicitado
         */

        return reservaGateway.create(reserva);
    }
}
