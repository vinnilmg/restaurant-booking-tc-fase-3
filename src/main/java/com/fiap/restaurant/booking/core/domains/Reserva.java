package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;

import java.time.LocalDateTime;

public interface Reserva {
    Long getId();

    String getCpf();

    String getStatus();

    LocalDateTime getDataHoraReserva();

    LocalDateTime getDataHoraCriacao();

    void updateStatus(StatusReservaEnum status);

    boolean isRequested();

    boolean isCanceled();

    boolean isConfirmed();
}
