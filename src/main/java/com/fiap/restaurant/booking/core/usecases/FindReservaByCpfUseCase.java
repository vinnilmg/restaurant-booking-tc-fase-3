package com.fiap.restaurant.booking.core.usecases;

import com.fiap.restaurant.booking.core.domains.Reserva;

@FunctionalInterface
public interface FindReservaByCpfUseCase {
    Reserva execute(String cpf);
}
