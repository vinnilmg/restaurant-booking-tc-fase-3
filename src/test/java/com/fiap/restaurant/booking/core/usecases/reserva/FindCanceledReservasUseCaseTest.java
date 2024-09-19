package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindCanceledReservasUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindCanceledReservasUseCaseTest {
    private FindCanceledReservasUseCaseImpl findCanceledReservasUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findCanceledReservasUseCase = new FindCanceledReservasUseCaseImpl(
                reservaGateway
        );
    }

    @Test
    void shouldFindCanceledReservas() {
        final List<Reserva> expected = List.of(ReservaDomainFixture.CANCELADA());

        when(reservaGateway.findByStatus(StatusReservaEnum.CANCELADA.name()))
                .thenReturn(expected);

        final var result = findCanceledReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(reservaGateway).findByStatus(StatusReservaEnum.CANCELADA.name());
    }

    @Test
    void shouldNotFindCanceledReservas() {
        when(reservaGateway.findByStatus(StatusReservaEnum.SOLICITADA.name()))
                .thenReturn(List.of());

        final var result = findCanceledReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isEmpty();

        verify(reservaGateway).findByStatus(StatusReservaEnum.CANCELADA.name());
    }
}
