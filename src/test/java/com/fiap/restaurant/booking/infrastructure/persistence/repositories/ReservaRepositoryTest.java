package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.utils.fixture.ReservaEntityFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_RESERVA_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReservaRepositoryTest {

    @Mock
    private ReservaRepository reservaRepository;
    private AutoCloseable mocks;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void shouldFindReservaById() {
        final var id = DEFAULT_RESERVA_ID;
        final var expected = ReservaEntityFixture.FULL();

        when(reservaRepository.findById(id))
                .thenReturn(Optional.of(expected));

        final var result = reservaRepository.findById(id);

        assertThat(result)
                .isNotNull()
                .isPresent()
                .hasValue(expected);

        verify(reservaRepository).findById(id);
    }

    @Test
    void shouldFindReservaByStatus() {
        final var status = StatusReservaEnum.SOLICITADA.toString();
        final var expected = List.of(ReservaEntityFixture.FULL());

        when(reservaRepository.findByStatus(status))
                .thenReturn(expected);

        final var result = reservaRepository.findByStatus(status);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(expected.size());

        verify(reservaRepository).findByStatus(status);
    }

    @Test
    void shouldFindReservaByCpfCliente() {
        final var cpf = DEFAULT_CPF;
        final var expected = List.of(ReservaEntityFixture.FULL());

        when(reservaRepository.findByCpfCliente(cpf))
                .thenReturn(expected);

        final var result = reservaRepository.findByCpfCliente(cpf);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(expected.size());

        verify(reservaRepository).findByCpfCliente(cpf);
    }

    @Test
    void shouldFindAllReservas() {
        final var expected = List.of(ReservaEntityFixture.FULL());

        when(reservaRepository.findAll())
                .thenReturn(expected);

        final var result = reservaRepository.findAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(expected.size());

        verify(reservaRepository).findAll();
    }

    @Test
    void shouldSaveReserva() {
        final var entity = ReservaEntityFixture.FULL();

        when(reservaRepository.save(entity))
                .thenAnswer(i -> i.getArgument(0));

        final var result = reservaRepository.save(entity);

        assertThat(result)
                .isNotNull()
                .isEqualTo(entity);

        verify(reservaRepository).save(entity);
    }
}
