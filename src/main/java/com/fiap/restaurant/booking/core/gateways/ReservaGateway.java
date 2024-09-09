package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaGateway {
    Reserva create(Reserva reserva);

    List<Reserva> getAll();

    Optional<Reserva> findByCpf(String cpf);
}
