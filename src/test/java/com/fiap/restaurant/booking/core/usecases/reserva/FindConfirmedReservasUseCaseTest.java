package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindConfirmedReservasUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindConfirmedReservasUseCaseTest {
    private FindConfirmedReservasUseCaseImpl findConfirmedReservasUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findConfirmedReservasUseCase = new FindConfirmedReservasUseCaseImpl(
                reservaGateway
        );
    }

    @Test
    void shouldFindConfirmedReservas() {
        final List<Reserva> expected = List.of(ReservaDomainFixture.CONFIRMADA());

        when(reservaGateway.findByStatus(StatusReservaEnum.CONFIRMADA.name()))
                .thenReturn(expected);

        final var result = findConfirmedReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(reservaGateway).findByStatus(StatusReservaEnum.CONFIRMADA.name());
    }

    @Test
    void shouldNotFindConfirmedReservas() {
        when(reservaGateway.findByStatus(StatusReservaEnum.CANCELADA.name()))
                .thenReturn(List.of());

        final var result = findConfirmedReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isEmpty();

        verify(reservaGateway).findByStatus(StatusReservaEnum.CONFIRMADA.name());
    }
}
