package com.fiap.restaurant.booking.core.usecases.reserva;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.exceptions.ValidationException;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.core.usecases.reserva.impl.FindReservaByCpfUseCaseImpl;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class FindReservaByCpfUseCaseTest {
    private FindReservaByCpfUseCaseImpl findReservaByCpfUseCase;
    private ReservaGateway reservaGateway;

    @BeforeEach
    void init() {
        reservaGateway = mock(ReservaGateway.class);
        findReservaByCpfUseCase = new FindReservaByCpfUseCaseImpl(
                reservaGateway
        );
    }

    @Test
    void shouldThrowValidationExceptionWhenCpfIsNull() {
        assertThatThrownBy(() -> findReservaByCpfUseCase.execute(null))
                .isInstanceOf(ValidationException.class)
                .hasMessage("CPF cannot be null");

        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldThrowValidationExceptionWhenCpfIsEmpty() {
        assertThatThrownBy(() -> findReservaByCpfUseCase.execute(""))
                .isInstanceOf(ValidationException.class)
                .hasMessage("CPF cannot be null");

        verifyNoInteractions(reservaGateway);
    }

    @Test
    void shouldFindReservaByCpf() {
        final var cpf = DEFAULT_CPF;
        final List<Reserva> expected = List.of(ReservaDomainFixture.SOLICITADA());

        when(reservaGateway.findByCpf(cpf)).thenReturn(expected);

        final var result = findReservaByCpfUseCase.execute(cpf);

        assertThat(result)
                .isNotNull()
                .hasSize(expected.size())
                .isEqualTo(expected);

        verify(reservaGateway).findByCpf(cpf);
    }
}
