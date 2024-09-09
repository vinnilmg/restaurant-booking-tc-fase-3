package com.fiap.restaurant.booking.core.domains;

import java.time.LocalDateTime;

public interface Reserva {
    Long getId();

    String getCpf();

    String getStatus();

    LocalDateTime getDataHoraReserva();

    LocalDateTime getDataHoraCriacao();
}
