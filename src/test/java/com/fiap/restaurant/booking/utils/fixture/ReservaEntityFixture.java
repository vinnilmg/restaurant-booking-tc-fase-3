package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;

import java.time.LocalDateTime;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;

public class ReservaEntityFixture {

    public static ReservaEntity BY_DOMAIN_WITH_ID(final Reserva reserva) {
        final var result = new ReservaEntity();
        result.setCpfCliente(reserva.getCpf());
        result.setStatus(reserva.getStatus());
        result.setDataHoraReserva(reserva.getDataHoraReserva());
        result.setDataHoraCriacao(reserva.getDataHoraCriacao());
        result.setId(1L);
        return result;
    }

    public static ReservaEntity FULL() {
        final var result = new ReservaEntity();
        result.setId(1L);
        result.setStatus(StatusReservaEnum.SOLICITADA.name());
        result.setCpfCliente(DEFAULT_CPF);
        result.setDataHoraReserva(LocalDateTime.now().plusDays(3));
        result.setDataHoraCriacao(LocalDateTime.now());
        return result;
    }

    public static ReservaEntity WITHOUT_ID() {
        final var result = new ReservaEntity();
        result.setStatus(StatusReservaEnum.SOLICITADA.name());
        result.setCpfCliente(DEFAULT_CPF);
        result.setDataHoraReserva(LocalDateTime.now().plusDays(3));
        result.setDataHoraCriacao(LocalDateTime.now());
        return result;
    }
}
