package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.ReservaEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.ReservaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservaRepositoryGateway implements ReservaGateway {
    private final ReservaRepository reservaRepository;
    private final ReservaEntityMapper reservaEntityMapper;

    public ReservaRepositoryGateway(
            ReservaRepository reservaRepository,
            ReservaEntityMapper reservaEntityMapper
    ) {
        this.reservaRepository = reservaRepository;
        this.reservaEntityMapper = reservaEntityMapper;
    }

    @Override
    public Reserva create(final Reserva reserva) {
        final var entity = reservaEntityMapper.toEntity(reserva);
        final var entitySaved = reservaRepository.save(entity);
        return reservaEntityMapper.toDomain(entitySaved);
    }

    @Override
    public List<Reserva> getAll() {
        final var entities = reservaRepository.findAll();
        return reservaEntityMapper.toDomains(entities);
    }

    @Override
    public Optional<Reserva> findByid(final Long id) {
        return reservaRepository.findById(id)
                .map(reservaEntityMapper::toDomain);
    }

    @Override
    public List<Reserva> findByCpf(final String cpf) {
        final var entities = reservaRepository.findByCpfCliente(cpf);
        return reservaEntityMapper.toDomains(entities);
    }

    @Override
    public List<Reserva> findByStatus(final String status) {
        final var entities = reservaRepository.findByStatus(status);
        return reservaEntityMapper.toDomains(entities);
    }

    @Override
    public void update(final Reserva reserva) {
        final var entity = reservaEntityMapper.toEntity(reserva);
        reservaRepository.save(entity);
    }
}
