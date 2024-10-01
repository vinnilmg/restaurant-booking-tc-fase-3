package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.infrastructure.controllers.response.ReservaResponse;

import static com.fiap.restaurant.booking.utils.DateTimeUtils.toDefaultFormat;

public class ReservaResponseFixture {

    public static ReservaResponse BY_DOMAIN(final Reserva reserva) {
        return new ReservaResponse(
                reserva.getId(),
                reserva.getCpf(),
                reserva.getMesa().getRestaurante().getNome(),
                reserva.getMesa().getNumeroDaMesa(),
                reserva.getStatus(),
                toDefaultFormat(reserva.getDataHoraReserva()),
                toDefaultFormat(reserva.getDataHoraCriacao())
        );
    }
}
