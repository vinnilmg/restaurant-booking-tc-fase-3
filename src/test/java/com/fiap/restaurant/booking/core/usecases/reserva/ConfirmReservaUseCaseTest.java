package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.ConfirmReservaUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_RESERVA_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ConfirmReservaUseCaseTest {
    private ConfirmReservaUseCaseImpl confirmReservaUseCase;
    private FindReservaByIdUseCase findReservaByIdUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        findReservaByIdUseCase = mock(FindReservaByIdUseCase.class);
        reservaGateway = mock(ReservaGateway.class);
        confirmReservaUseCase = new ConfirmReservaUseCaseImpl(
                findReservaByIdUseCase,
                reservaGateway
        );
    }

    @Test
    void shouldConfirmReserva() {
        final var id = DEFAULT_RESERVA_ID;
        final var reserva = ReservaDomainFixture.SOLICITADA();

        when(findReservaByIdUseCase.execute(id)).thenReturn(reserva);
        doNothing().when(reservaGateway).update(reserva);

        confirmReservaUseCase.execute(id);

        assertThat(reserva.getStatus()).isEqualTo(StatusReservaEnum.CONFIRMADA.name());

        verify(findReservaByIdUseCase).execute(id);
        verify(reservaGateway).update(reserva);
    }

    @Test
    void shouldThrowValidationExceptionWhenReservaIsAlreadyConfirmed() {
        final var id = DEFAULT_RESERVA_ID;
        final var reserva = ReservaDomainFixture.CONFIRMADA();

        when(findReservaByIdUseCase.execute(id)).thenReturn(reserva);

        assertThatThrownBy(() -> confirmReservaUseCase.execute(id))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Reserva is already confirmed");

        verify(findReservaByIdUseCase).execute(id);
        verifyNoInteractions(reservaGateway);
    }
}
