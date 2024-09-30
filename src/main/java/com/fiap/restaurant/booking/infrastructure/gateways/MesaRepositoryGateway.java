package com.fiap.restaurant.booking.infrastructure.gateways;

import com.fiap.restaurant.booking.core.domains.Mesa;
import com.fiap.restaurant.booking.core.domains.MesaDomain;
import com.fiap.restaurant.booking.core.domains.enums.StatusMesaEnum;
import com.fiap.restaurant.booking.core.gateways.MesaGateway;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.MesaEntityMapper;
import com.fiap.restaurant.booking.infrastructure.persistence.repositories.MesaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public   class MesaRepositoryGateway implements MesaGateway {
    private final MesaRepository mesaRepository;
    private final MesaEntityMapper mesaEntityMapper;

    @Override
    public Mesa create(final Mesa mesa) {
        return mesaEntityMapper.toDomain(
                mesaRepository.save(mesaEntityMapper.toEntity(mesa)
                )
        );
    }

    @Override
    public Optional<Mesa> findById(Long id) {
        return mesaRepository.findById(id)
                .map(mesaEntityMapper::toDomain);
    }

    @Override
    public Optional<Mesa> findById(Long id, Integer numeroMesa) {
        return Optional.empty();
    }

    @Override
    public List<Mesa> findByStatus(StatusMesaEnum status) {
        List<MesaEntity> mesaEntities = mesaRepository.findByStatus(String.valueOf(status));
        List<Mesa> mesaDomains = mesaEntityMapper.toDomains(mesaEntities);
        return mesaDomains;
//        return mesaEntityMapper.toDomains(mesaEntities);
    }

    @Transactional
    @Override
    public void delete(Long id, Integer numeroMesa) {
        mesaRepository.deleteByRestauranteIdAndNumeroDaMesa(id, numeroMesa);
        mesaRepository.flush();
    }

}
