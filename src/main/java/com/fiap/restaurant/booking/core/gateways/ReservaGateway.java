package com.fiap.restaurant.booking.core.gateways;

import com.fiap.restaurant.booking.core.domains.Reserva;

public interface ReservaGateway {
    Reserva create(Reserva reserva);
}
