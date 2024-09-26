package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindRequestedReservasUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindRequestedReservasUseCaseTest {
    private FindRequestedReservasUseCaseImpl findRequestedReservasUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findRequestedReservasUseCase = new FindRequestedReservasUseCaseImpl(
                reservaGateway
        );
    }

    @Test
    void shouldFindRequestedReservas() {
        final List<Reserva> expected = List.of(ReservaDomainFixture.SOLICITADA());

        when(reservaGateway.findByStatus(StatusReservaEnum.SOLICITADA.name()))
                .thenReturn(expected);

        final var result = findRequestedReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(reservaGateway).findByStatus(StatusReservaEnum.SOLICITADA.name());
    }

    @Test
    void shouldNotFindRequestedReservas() {
        when(reservaGateway.findByStatus(StatusReservaEnum.CANCELADA.name()))
                .thenReturn(List.of());

        final var result = findRequestedReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isEmpty();

        verify(reservaGateway).findByStatus(StatusReservaEnum.SOLICITADA.name());
    }
}
