package com.fiap.restaurant.booking.core.usecases.reserva.impl;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.FindReservaByCpfUseCase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class FindReservaByCpfUseCaseImpl implements FindReservaByCpfUseCase {
    private final ReservaGateway reservaGateway;

    public FindReservaByCpfUseCaseImpl(ReservaGateway reservaGateway) {
        this.reservaGateway = reservaGateway;
    }

    @Override
    public List<Reserva> execute(final String cpf) {
        if (isEmpty(cpf)) throw ValidationException.of("CPF", "cannot be null");
        return reservaGateway.findByCpf(cpf);
    }
}
