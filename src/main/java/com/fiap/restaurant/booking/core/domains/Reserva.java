package com.fiap.restaurant.booking.core.domains;

import java.time.LocalDateTime;

public interface Reserva {
    Long id();

    String cpf();

    String status();

    LocalDateTime dataHoraReserva();
}
