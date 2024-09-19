package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.fiap.restaurant.booking.utils.DateTimeUtils.toLocalDateTime;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservaDomainTest {

    @Test
    void shouldThrowValidationExceptionWhenCPFIsNull() {
        assertThatThrownBy(() -> new ReservaDomain(
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Customer CPF cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenCPFDoesNotContain11Positions() {
        assertThatThrownBy(() -> new ReservaDomain(
                "123456789",
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Customer CPF must be 11 positions");
    }

    @Test
    void shouldThrowValidationExceptionWhenStatusIsNull() {
        assertThatThrownBy(() -> new ReservaDomain(
                DEFAULT_CPF,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Status cannot be null or empty");
    }

    @Test
    void shouldThrowValidationExceptionWhenStatusIsEmpty() {
        assertThatThrownBy(() -> new ReservaDomain(
                DEFAULT_CPF,
                "",
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Status cannot be null or empty");
    }

    @Test
    void shouldThrowValidationExceptionWhenStatusIsNotAccepted() {
        assertThatThrownBy(() -> new ReservaDomain(
                DEFAULT_CPF,
                "whatever",
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Status is invalid");
    }

    @Test
    void shouldThrowValidationExceptionWhenDataHoraReservaIsNull() {
        assertThatThrownBy(() -> new ReservaDomain(
                DEFAULT_CPF,
                StatusReservaEnum.SOLICITADA.name(),
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Data Hora da Reserva cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenDataHoraReservaIsOld() {
        assertThatThrownBy(() -> new ReservaDomain(
                DEFAULT_CPF,
                StatusReservaEnum.SOLICITADA.name(),
                "2000-01-01 01:00"))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Data Hora da Reserva must be a future date");
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> new ReservaDomain(
                null,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Reserva Id cannot be null");
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNegative() {
        assertThatThrownBy(() -> new ReservaDomain(
                -1L,
                null,
                null,
                null,
                null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Reserva Id cannot be negative");
    }

    @Test
    void shouldConstructReservaDomain() {
        final var cpf = DEFAULT_CPF;
        final var status = StatusReservaEnum.SOLICITADA.name();
        final var dataHoraReserva = LocalDateTime.now()
                .plusDays(5)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        final var result = new ReservaDomain(
                cpf,
                status,
                dataHoraReserva
        );

        assertThat(result)
                .isNotNull()
                .isInstanceOf(Reserva.class)
                .extracting(Reserva::getCpf, Reserva::getStatus, Reserva::getDataHoraReserva)
                .containsExactly(cpf, status, toLocalDateTime(dataHoraReserva));

        assertThat(result)
                .extracting(Reserva::getId, Reserva::getDataHoraCriacao)
                .containsOnlyNulls();
    }

    @Test
    void shouldConstructReservaDomainWithId() {
        final var id = 1L;
        final var cpf = DEFAULT_CPF;
        final var status = StatusReservaEnum.SOLICITADA.name();
        final var dataHoraReserva = LocalDateTime.now().plusDays(5);
        final var dataHoraCriacao = LocalDateTime.now();

        final var result = new ReservaDomain(
                1L,
                cpf,
                status,
                dataHoraReserva,
                dataHoraCriacao
        );

        assertThat(result)
                .isNotNull()
                .isInstanceOf(Reserva.class)
                .extracting(
                        Reserva::getId,
                        Reserva::getCpf,
                        Reserva::getStatus,
                        Reserva::getDataHoraReserva,
                        Reserva::getDataHoraCriacao
                ).containsExactly(
                        id,
                        cpf,
                        status,
                        dataHoraReserva,
                        dataHoraCriacao
                );
    }

    @Test
    void shouldReturnTrueWhenReservaIsRequested() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        assertThat(reserva)
                .extracting(Reserva::isRequested, Reserva::isConfirmed, Reserva::isCanceled)
                .containsExactly(true, false, false);
    }

    @Test
    void shouldReturnTrueWhenReservaIsCanceled() {
        final var reserva = ReservaDomainFixture.CANCELADA();
        assertThat(reserva)
                .extracting(Reserva::isRequested, Reserva::isConfirmed, Reserva::isCanceled)
                .containsExactly(false, false, true);
    }

    @Test
    void shouldReturnTrueWhenReservaIsConfirmed() {
        final var reserva = ReservaDomainFixture.CONFIRMADA();
        assertThat(reserva)
                .extracting(Reserva::isRequested, Reserva::isConfirmed, Reserva::isCanceled)
                .containsExactly(false, true, false);
    }

    @Test
    void shouldUpdateStatus() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        assertThat(reserva.isRequested())
                .isTrue();

        reserva.updateStatus(StatusReservaEnum.CANCELADA);

        assertThat(reserva.isRequested())
                .isFalse();

        assertThat(reserva.isCanceled())
                .isTrue();
    }
}
