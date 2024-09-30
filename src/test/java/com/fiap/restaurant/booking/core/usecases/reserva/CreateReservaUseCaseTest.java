package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.mesa.FindMesaByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.CreateReservaUseCaseImpl;
import com.fiap.restaurant.booking.core.usecases.restaurante.FindRestauranteByIdUseCase;
import com.fiap.restaurant.booking.utils.fixture.MesaDomainFixture;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import com.fiap.restaurant.booking.utils.fixture.RestauranteDomainFixture;
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
    private FindRestauranteByIdUseCase findRestauranteByIdUseCase;
    private FindMesaByIdUseCase findMesaByIdUseCase;
    private FindReservaByCpfUseCase findReservaByCpfUseCase;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findReservaByCpfUseCase = mock(FindReservaByCpfUseCase.class);
        findRestauranteByIdUseCase = mock(FindRestauranteByIdUseCase.class);
        findMesaByIdUseCase = mock(FindMesaByIdUseCase.class);
        createReservaUseCase = new CreateReservaUseCaseImpl(
                reservaGateway,
                findRestauranteByIdUseCase,
                findMesaByIdUseCase,
                findReservaByCpfUseCase
        );
    }

    @Test
    void shouldCreateReserva() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var restauranteId = 1L;
        final var mesaId = 1L;

        when(findRestauranteByIdUseCase.execute(restauranteId))
                .thenReturn(RestauranteDomainFixture.FULL_WITH_ID(restauranteId));

        when(findMesaByIdUseCase.execute(mesaId))
                .thenReturn(MesaDomainFixture.FULL_WITH_IDS(mesaId, restauranteId));

        when(findReservaByCpfUseCase.execute(reserva.getCpf()))
                .thenReturn(List.of());

        when(reservaGateway.create(reserva))
                .thenAnswer(i -> i.getArguments()[0]);

        final var result = createReservaUseCase.execute(restauranteId, mesaId, reserva);

        assertThat(result)
                .isNotNull()
                .isEqualTo(reserva);

        verify(findRestauranteByIdUseCase).execute(restauranteId);
        verify(findMesaByIdUseCase).execute(mesaId);
        verify(findReservaByCpfUseCase).execute(reserva.getCpf());
        verify(reservaGateway).create(reserva);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateReservaWithDifferentRestaurantFoundById() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var restauranteId = 1L;
        final var mesaId = 1L;

        when(findRestauranteByIdUseCase.execute(restauranteId))
                .thenReturn(RestauranteDomainFixture.FULL_WITH_ID(2L));

        assertThatThrownBy(() -> createReservaUseCase.execute(restauranteId, mesaId, reserva))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Different restaurant found");

        verify(findRestauranteByIdUseCase).execute(restauranteId);
        verifyNoInteractions(findMesaByIdUseCase);
        verifyNoInteractions(findReservaByCpfUseCase);
        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCreateReservaWithDifferentTableFoundById() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var restauranteId = 1L;
        final var mesaId = 1L;

        when(findRestauranteByIdUseCase.execute(restauranteId))
                .thenReturn(RestauranteDomainFixture.FULL_WITH_ID(restauranteId));

        when(findMesaByIdUseCase.execute(mesaId))
                .thenReturn(MesaDomainFixture.FULL_WITH_IDS(2L, restauranteId));

        assertThatThrownBy(() -> createReservaUseCase.execute(restauranteId, mesaId, reserva))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Different table found");

        verify(findRestauranteByIdUseCase).execute(restauranteId);
        verify(findMesaByIdUseCase).execute(mesaId);
        verifyNoInteractions(findReservaByCpfUseCase);
        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCreateReservaWithUserAlreadyContainsReservaInProgress() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var restauranteId = 1L;
        final var mesaId = 1L;

        when(findRestauranteByIdUseCase.execute(restauranteId))
                .thenReturn(RestauranteDomainFixture.FULL_WITH_ID(restauranteId));

        when(findMesaByIdUseCase.execute(mesaId))
                .thenReturn(MesaDomainFixture.FULL_WITH_IDS(mesaId, restauranteId));

        when(findReservaByCpfUseCase.execute(reserva.getCpf()))
                .thenReturn(List.of(reserva));

        assertThatThrownBy(() -> createReservaUseCase.execute(restauranteId, mesaId, reserva))
                .isInstanceOf(ValidationException.class)
                .hasMessage("User already contains booking in progress");

        verify(findRestauranteByIdUseCase).execute(restauranteId);
        verify(findMesaByIdUseCase).execute(mesaId);
        verify(findReservaByCpfUseCase).execute(reserva.getCpf());
        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCreateReservaWithReservedTable() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var restauranteId = 1L;
        final var mesaId = 1L;

        when(findRestauranteByIdUseCase.execute(restauranteId))
                .thenReturn(RestauranteDomainFixture.FULL_WITH_ID(restauranteId));

        when(findMesaByIdUseCase.execute(mesaId))
                .thenReturn(MesaDomainFixture.FULL_WITH_ID_AND_RESERVADA(mesaId, restauranteId));

        when(findReservaByCpfUseCase.execute(reserva.getCpf()))
                .thenReturn(List.of());

        assertThatThrownBy(() -> createReservaUseCase.execute(restauranteId, mesaId, reserva))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Mesa is already reserved");

        verify(findRestauranteByIdUseCase).execute(restauranteId);
        verify(findMesaByIdUseCase).execute(mesaId);
        verify(findReservaByCpfUseCase).execute(reserva.getCpf());
        verifyNoInteractions(reservaGateway);
    }
}
