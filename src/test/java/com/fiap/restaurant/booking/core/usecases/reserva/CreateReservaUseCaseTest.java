package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.CreateReservaUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class CreateReservaUseCaseTest {
    private CreateReservaUseCaseImpl createReservaUseCase;
    private ReservaGateway reservaGateway;
    private FindReservaByCpfUseCase findReservaByCpfUseCase;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findReservaByCpfUseCase = mock(FindReservaByCpfUseCase.class);
        createReservaUseCase = new CreateReservaUseCaseImpl(
                reservaGateway,
                findReservaByCpfUseCase
        );
    }

    @Test
    void shouldCreateReserva() {
        final var reserva = ReservaDomainFixture.SOLICITADA();

        when(findReservaByCpfUseCase.execute(reserva.getCpf()))
                .thenReturn(List.of());

        when(reservaGateway.create(reserva))
                .thenAnswer(i -> i.getArguments()[0]);

        final var result = createReservaUseCase.execute(reserva);

        assertThat(result)
                .isNotNull()
                .isEqualTo(reserva);

        verify(findReservaByCpfUseCase).execute(reserva.getCpf());
        verify(reservaGateway).create(reserva);
    }

    @Test
    void shouldThrowValidationExceptionWhenCreateReservaWithUserAlreadyContainsReservaInProgress() {
        final var reserva = ReservaDomainFixture.SOLICITADA();

        when(findReservaByCpfUseCase.execute(reserva.getCpf()))
                .thenReturn(List.of(reserva));

        assertThatThrownBy(() -> createReservaUseCase.execute(reserva))
                .isInstanceOf(ValidationException.class)
                .hasMessage("User already contains booking in progress");

        verify(findReservaByCpfUseCase).execute(reserva.getCpf());
        verifyNoInteractions(reservaGateway);
    }
}
