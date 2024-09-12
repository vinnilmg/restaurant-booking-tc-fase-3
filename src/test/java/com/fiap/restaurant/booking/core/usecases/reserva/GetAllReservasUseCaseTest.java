package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.GetAllReservasUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllReservasUseCaseTest {
    private GetAllReservasUseCaseImpl getAllReservasUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        getAllReservasUseCase = new GetAllReservasUseCaseImpl(
                reservaGateway
        );
    }

    @Test
    void shouldGetAllReservas() {
        final List<Reserva> reservas = List.of(
                ReservaDomainFixture.SOLICITADA(),
                ReservaDomainFixture.CANCELADA()
        );

        when(reservaGateway.getAll()).thenReturn(reservas);

        final var result = getAllReservasUseCase.execute();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(reservas.size());

        verify(reservaGateway).getAll();
    }
}
