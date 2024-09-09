package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByCpfUseCase;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class CreateReservaUseCaseImpl implements CreateReservaUseCase {
    private final ReservaGateway reservaGateway;
    private final FindReservaByCpfUseCase findReservaByCpfUseCase;

    public CreateReservaUseCaseImpl(
            ReservaGateway reservaGateway,
            FindReservaByCpfUseCase findReservaByCpfUseCase
    ) {
        this.reservaGateway = reservaGateway;
        this.findReservaByCpfUseCase = findReservaByCpfUseCase;
    }

    @Override
    public Reserva execute(final Reserva reserva) {
        /*
         * TODO:
         *  - Verificar se existe uma reserva em andamento para o cliente - OK
         *  - Verificar se o restaurante + a mesa existem
         *  - Verifica se o restaurante está em aberto no período solicitado
         */

        final var userBookings = findReservaByCpfUseCase.execute(reserva.getCpf());
        if (isNotEmpty(userBookings)) {
            final var containsBookingInProgress = userBookings.stream().anyMatch(Reserva::isRequested);
            if (containsBookingInProgress) {
                throw ValidationException.of("User", "already contains booking in progress");
            }
        }

        return reservaGateway.create(reserva);
    }
}
