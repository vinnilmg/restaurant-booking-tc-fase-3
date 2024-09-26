package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.ReservaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;

import java.time.LocalDateTime;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_RESERVA_ID;

public class ReservaDomainFixture {

    public static ReservaDomain SOLICITADA() {
        return new ReservaDomain(
                DEFAULT_RESERVA_ID,
                DEFAULT_CPF,
                StatusReservaEnum.SOLICITADA.name(),
                LocalDateTime.now().plusDays(5),
                LocalDateTime.now()
        );
    }

    public static ReservaDomain CANCELADA() {
        return new ReservaDomain(
                DEFAULT_RESERVA_ID,
                DEFAULT_CPF,
                StatusReservaEnum.CANCELADA.name(),
                LocalDateTime.now().plusDays(5),
                LocalDateTime.now()
        );
    }

    public static ReservaDomain CONFIRMADA() {
        return new ReservaDomain(
                DEFAULT_RESERVA_ID,
                DEFAULT_CPF,
                StatusReservaEnum.CONFIRMADA.name(),
                LocalDateTime.now().plusDays(5),
                LocalDateTime.now()
        );
    }

    public static ReservaDomain BY_ENTITY(final ReservaEntity entity) {
        return new ReservaDomain(
                entity.getId(),
                entity.getCpfCliente(),
                entity.getStatus(),
                entity.getDataHoraReserva(),
                entity.getDataHoraCriacao()
        );
    }
}
