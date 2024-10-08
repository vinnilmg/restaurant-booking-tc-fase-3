package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.DateTimeUtils;

import java.time.LocalDateTime;

import static com.fiap.restaurant.booking.utils.DateTimeUtils.toDefaultFormat;
import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.util.Objects.isNull;

public class ReservaDomain implements Reserva {
    private final String cpf;
    private final LocalDateTime dataHoraReserva;
    private Long id;
    private LocalDateTime dataHoraCriacao;
    private StatusReservaEnum status;
    private Mesa mesa;

    public ReservaDomain(
            final Long id,
            final String cpf,
            final String status,
            final LocalDateTime dataHoraReserva,
            final LocalDateTime dataHoraCriacao,
            final Mesa mesa
    ) {
        this.id = idValidation(id);
        this.cpf = cpfValidation(cpf);
        this.status = statusValidation(status);
        this.dataHoraReserva = dataHoraReserva;
        this.dataHoraCriacao = dataHoraCriacao;
        this.mesa = mesa;
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
    public Mesa getMesa() {
        return mesa;
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
    public String getDataHoraReservaFormatted() {
        return toDefaultFormat(this.dataHoraReserva);
    }

    @Override
    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    @Override
    public String getDataHoraCriacaoFormatted() {
        return toDefaultFormat(this.dataHoraCriacao);
    }

    @Override
    public void updateStatus(final StatusReservaEnum status) {
        this.status = status;
    }

    @Override
    public boolean isRequested() {
        return status.equals(StatusReservaEnum.SOLICITADA);
    }

    @Override
    public boolean isCanceled() {
        return status.equals(StatusReservaEnum.CANCELADA);
    }

    @Override
    public boolean isConfirmed() {
        return status.equals(StatusReservaEnum.CONFIRMADA);
    }

    @Override
    public void fillMesa(Mesa mesa) {
        this.mesa = mesa;
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
