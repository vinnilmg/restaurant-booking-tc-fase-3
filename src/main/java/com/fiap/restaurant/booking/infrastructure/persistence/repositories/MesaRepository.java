package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.MesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesaRepository extends JpaRepository<MesaEntity, Long> {
    //    List<MesaEntity> findByIdDoRestaurante(Long idDoRestaurante);
    List<MesaEntity> findByStatus(String status);
}
