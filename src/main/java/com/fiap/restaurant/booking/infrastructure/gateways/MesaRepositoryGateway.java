package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.MesaEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.MesaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MesaRepositoryGateway implements MesaGateway {

    private final MesaRepository mesaRepository;
    private final MesaEntityMapper mesaEntityMapper;

    @Override
    public MesaDomain create(MesaDomain mesa) {
        return mesaEntityMapper.toDomain(
                mesaRepository.save(mesaEntityMapper.toEntity(mesa)
                )
        );
    }

    @Override
    public Optional<MesaDomain> findById(Long id) {
        return mesaRepository.findById(id)
                .map(mesaEntityMapper::toDomain);
    }

    @Override
    public List<MesaDomain> findByStatus(String status) {
        return List.of();
    }

    @Override
    public List<MesaDomain> findByRestauranteId(Long restauranteId) {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
