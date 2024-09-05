package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.Reserva;
import com.fiap.restaurant.booking.core.gateways.ReservaGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.ReservaEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.ReservaRepository;
import org.springframework.stereotype.Component;

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
        return reservaEntityMapper.toDomain(
                reservaRepository.save(entity)
        );
    }
}
