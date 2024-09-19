package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.ReservaEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.ReservaRepository;
import com.fiap.restaurant.booking.utils.fixture.ReservaDomainFixture;
import com.fiap.restaurant.booking.utils.fixture.ReservaEntityFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CPF;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReservaRepositoryGatewayTest {
    private ReservaRepositoryGateway reservaRepositoryGateway;
    private ReservaRepository reservaRepository;
    private ReservaEntityMapper reservaEntityMapper;

    @BeforeEach
    void init() {
        reservaRepository = mock(ReservaRepository.class);
        reservaEntityMapper = mock(ReservaEntityMapper.class);
        reservaRepositoryGateway = new ReservaRepositoryGateway(
                reservaRepository,
                reservaEntityMapper
        );
    }

    @Test
    void shouldCreateReserva() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var entity = ReservaEntityFixture.BY_DOMAIN_WITH_ID(reserva);
        final var expected = ReservaDomainFixture.BY_ENTITY(entity);

        when(reservaEntityMapper.toEntity(reserva))
                .thenReturn(entity);

        when(reservaRepository.save(entity))
                .thenAnswer(i -> i.getArguments()[0]);

        when(reservaEntityMapper.toDomain(entity))
                .thenReturn(expected);

        final var result = reservaRepositoryGateway.create(reserva);

        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);

        verify(reservaEntityMapper).toEntity(reserva);
        verify(reservaRepository).save(entity);
        verify(reservaEntityMapper).toDomain(entity);
    }

    @Test
    void shouldGetAllReservas() {
        final var entities = List.of(ReservaEntityFixture.FULL());
        final List<Reserva> expected = List.of(ReservaDomainFixture.SOLICITADA());

        when(reservaRepository.findAll())
                .thenReturn(entities);

        when(reservaEntityMapper.toDomains(entities))
                .thenReturn(expected);

        final var result = reservaRepositoryGateway.getAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(entities.size());

        verify(reservaRepository).findAll();
        verify(reservaEntityMapper).toDomains(entities);
    }

    @Test
    void shouldFindReservaById() {
        final var reservaId = 1L;
        final var entity = ReservaEntityFixture.FULL();
        final var expected = ReservaDomainFixture.BY_ENTITY(entity);

        when(reservaRepository.findById(reservaId))
                .thenReturn(Optional.of(entity));

        when(reservaEntityMapper.toDomain(entity))
                .thenReturn(expected);

        final var result = reservaRepositoryGateway.findById(reservaId);

        assertThat(result)
                .isNotNull()
                .isPresent()
                .hasValue(expected);

        verify(reservaRepository).findById(reservaId);
        verify(reservaEntityMapper).toDomain(entity);
    }

    @Test
    void shouldFindReservasByCpf() {
        final var cpf = DEFAULT_CPF;
        final var entities = List.of(ReservaEntityFixture.FULL());
        final List<Reserva> expected = List.of(ReservaDomainFixture.SOLICITADA());

        when(reservaRepository.findByCpfCliente(cpf))
                .thenReturn(entities);

        when(reservaEntityMapper.toDomains(entities))
                .thenReturn(expected);

        final var result = reservaRepositoryGateway.findByCpf(cpf);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(entities.size());

        verify(reservaRepository).findByCpfCliente(cpf);
        verify(reservaEntityMapper).toDomains(entities);
    }

    @Test
    void shouldFindReservasByStatus() {
        final var status = StatusReservaEnum.SOLICITADA.name();
        final var entities = List.of(ReservaEntityFixture.FULL());
        final List<Reserva> expected = List.of(ReservaDomainFixture.SOLICITADA());

        when(reservaRepository.findByStatus(status))
                .thenReturn(entities);

        when(reservaEntityMapper.toDomains(entities))
                .thenReturn(expected);

        final var result = reservaRepositoryGateway.findByStatus(status);

        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .hasSize(entities.size());

        verify(reservaRepository).findByStatus(status);
        verify(reservaEntityMapper).toDomains(entities);
    }

    @Test
    void shouldUpdateReserva() {
        final var reserva = ReservaDomainFixture.SOLICITADA();
        final var entity = ReservaEntityFixture.BY_DOMAIN_WITH_ID(reserva);

        when(reservaEntityMapper.toEntity(reserva))
                .thenReturn(entity);

        when(reservaRepository.save(entity))
                .thenAnswer(i -> i.getArgument(0));

        reservaRepositoryGateway.update(reserva);

        verify(reservaEntityMapper).toEntity(reserva);
        verify(reservaRepository).save(entity);
    }
}
