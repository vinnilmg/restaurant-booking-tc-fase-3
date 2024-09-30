package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<MesaEntity, Long>
{
    Optional<MesaEntity> findByRestauranteIdAndNumeroDaMesa(Long idRestaurante, Integer numeroMesa );

    List<MesaEntity> findByStatus(String status);

    void deleteByRestauranteIdAndNumeroDaMesa(Long restauranteId, Integer numeroDaMesa);
}