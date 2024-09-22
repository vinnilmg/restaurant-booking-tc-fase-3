package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.core.domains.enums.StatusReservaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import com.fiap.restaurant.booking.utils.fixture.ReservaEntityFixture;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_RESERVA_ID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class ReservaRepositoryIT {

    @Autowired
    private ReservaRepository reservaRepository;

    @Test
    void shouldCountReservas() {
        final var result = reservaRepository.count();
        assertThat(result)
                .isPositive();
    }

    @Test
    void shouldFindReservaById() {
        final var id = DEFAULT_RESERVA_ID;
        final var result = reservaRepository.findById(id);

        assertThat(result)
                .isNotNull()
                .isPresent();
    }

    @Test
    void shouldFindReservasByStatus() {
        final var status = StatusReservaEnum.SOLICITADA.toString();
        final var result = reservaRepository.findByStatus(status);

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldFindReservasByCpf() {
        final var cpf = "26407243041";
        final var result = reservaRepository.findByCpfCliente(cpf);

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldFindAllReservas() {
        final var result = reservaRepository.findAll();

        assertThat(result)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void shouldSaveReserva() {
        final var reserva = ReservaEntityFixture.WITHOUT_ID();
        final var result = reservaRepository.save(reserva);

        assertThat(result)
                .isNotNull()
                .extracting(
                        ReservaEntity::getCpfCliente,
                        ReservaEntity::getStatus,
                        ReservaEntity::getDataHoraReserva,
                        ReservaEntity::getDataHoraCriacao
                ).containsExactly(
                        reserva.getCpfCliente(),
                        reserva.getStatus(),
                        reserva.getDataHoraReserva(),
                        reserva.getDataHoraCriacao()
                );

        assertThat(result.getId())
                .isNotNull();
    }
}
