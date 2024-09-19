package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.exceptions.NotFoundException;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindReservaByIdUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_RESERVA_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindReservaByIdUseCaseTest {
    private FindReservaByIdUseCaseImpl findReservaByIdUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findReservaByIdUseCase = new FindReservaByIdUseCaseImpl(
                reservaGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> findReservaByIdUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Reserva Id cannot be null or negative");

        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenIdIsNegative() {
        assertThatThrownBy(() -> findReservaByIdUseCase.execute(-1L))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Reserva Id cannot be null or negative");

        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenReservaIsNotFound() {
        final var id = DEFAULT_RESERVA_ID;

        when(reservaGateway.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findReservaByIdUseCase.execute(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Reserva not found");

        verify(reservaGateway).findById(id);
    }

    @Test
    void shouldFindReservaById() {
        final var id = DEFAULT_RESERVA_ID;
        final var expected = ReservaDomainFixture.SOLICITADA();

        when(reservaGateway.findById(id)).thenReturn(Optional.of(expected));

        final var result = findReservaByIdUseCase.execute(id);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);

        verify(reservaGateway).findById(id);
    }
}
