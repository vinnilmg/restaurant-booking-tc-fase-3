package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.BookMesaUseCase;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.CreateReservaUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByCpfUseCase;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

public class CreateReservaUseCaseImpl implements CreateReservaUseCase {
    private final ReservaGateway reservaGateway;
    private final FindRestauranteByIdUseCase findRestauranteByIdUseCase;
    private final FindMesaByIdUseCase findMesaByIdUseCase;
    private final FindReservaByCpfUseCase findReservaByCpfUseCase;
    private final BookMesaUseCase bookMesaUseCase;

    public CreateReservaUseCaseImpl(
            ReservaGateway reservaGateway,
            FindRestauranteByIdUseCase findRestauranteByIdUseCase,
            FindMesaByIdUseCase findMesaByIdUseCase,
            FindReservaByCpfUseCase findReservaByCpfUseCase,
            BookMesaUseCase bookMesaUseCase
    ) {
        this.reservaGateway = reservaGateway;
        this.findRestauranteByIdUseCase = findRestauranteByIdUseCase;
        this.findMesaByIdUseCase = findMesaByIdUseCase;
        this.findReservaByCpfUseCase = findReservaByCpfUseCase;
        this.bookMesaUseCase = bookMesaUseCase;
    }

    @Override
    public Reserva execute(final Long restauranteId, final Long mesaId, final Reserva reserva) {
        final var restaurante = findRestauranteByIdUseCase.execute(restauranteId);
        if (!restaurante.getId().equals(restauranteId)) {
            throw new IllegalArgumentException("Different restaurant found");
        }

        final var mesa = findMesaByIdUseCase.execute(mesaId);
        if (!mesa.getId().equals(mesaId) || !mesa.getRestaurante().getId().equals(restauranteId)) {
            throw new IllegalArgumentException("Different table found");
        }

        final var userBookings = findReservaByCpfUseCase.execute(reserva.getCpf());
        if (isNotEmpty(userBookings)) {
            final var containsBookingInProgress = userBookings.stream().anyMatch(Reserva::isRequested);
            if (containsBookingInProgress) {
                throw ValidationException.of("User", "already contains booking in progress");
            }
        }

        final var horaReserva = reserva.getDataHoraReserva().getHour();
        if (horaReserva < restaurante.getInicioFuncionamento().getHour()
                || horaReserva > restaurante.getFimFuncionamento().getHour()) {
            throw new IllegalArgumentException("Restaurant is closed");
        }

        if (mesa.getStatus().equals(StatusMesaEnum.RESERVADA)) {
            throw ValidationException.of("Mesa", "is already reserved");
        }

        reserva.fillMesa(mesa);

        final var createdReserva = reservaGateway.create(reserva);

        bookMesaUseCase.execute(mesaId);

        return createdReserva;
    }
}
