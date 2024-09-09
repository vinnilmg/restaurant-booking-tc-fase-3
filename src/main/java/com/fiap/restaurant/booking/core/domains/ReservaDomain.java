package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.DateTimeUtils;

import java.time.LocalDateTime;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.util.Objects.isNull;

public class ReservaDomain implements Reserva {
    private Long id;
    private LocalDateTime dataHoraCriacao;
    private final String cpf;
    private final StatusReservaEnum status;
    private LocalDateTime dataHoraReserva;

    public ReservaDomain(
            final Long id,
            final String cpf,
            final String status,
            final LocalDateTime dataHoraReserva,
            final LocalDateTime dataHoraCriacao
    ) {
        this.id = idValidation(id);
        this.cpf = cpfValidation(cpf);
        this.status = statusValidation(status);
        this.dataHoraReserva = dataHoraReserva;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public ReservaDomain(
            final String cpf,
            final String status,
            final String dataHoraReserva
    ) {
        this.cpf = cpfValidation(cpf);
        this.status = statusValidation(status);
        this.dataHoraReserva = dataHoraReservaValidation(dataHoraReserva);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public String getStatus() {
        return status.name();
    }

    @Override
    public LocalDateTime getDataHoraReserva() {
        return dataHoraReserva;
    }

    @Override
    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    private static Long idValidation(final Long id) {
        if (isNull(id)) throw ValidationException.of("Reserva Id", "cannot be null");
        if (id < 0) throw ValidationException.of("Reserva Id", "cannot be negative");
        return id;
    }

    private static String cpfValidation(final String cpf) {
        if (isNull(cpf)) throw ValidationException.of("Customer CPF", "cannot be null");
        if (cpf.length() < 11) throw ValidationException.of("Customer CPF", "must be 11 positions");
        return cpf;
    }

    private static StatusReservaEnum statusValidation(final String status) {
        if (isEmpty(status)) throw ValidationException.of("Status", "cannot be null or empty");
        final var statusEnum = StatusReservaEnum.toEnum(status);
        if (statusEnum.isEmpty()) throw ValidationException.of("Status", "is invalid");
        return statusEnum.get();
    }

    private static LocalDateTime dataHoraReservaValidation(final String dataHoraReserva) {
        if (isNull(dataHoraReserva)) throw ValidationException.of("Data Hora da Reserva", "cannot be null");
        final var parsedDate = DateTimeUtils.toLocalDateTime(dataHoraReserva);
        if (!DateTimeUtils.isFutureDate(parsedDate)) throw ValidationException.of("Data Hora da Reserva", "must be a future date");
        return parsedDate;
    }
}
