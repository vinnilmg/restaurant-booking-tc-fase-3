package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    List<ReservaEntity> findByCpfCliente(String cpf);

    List<ReservaEntity> findByStatus(String status);
}
