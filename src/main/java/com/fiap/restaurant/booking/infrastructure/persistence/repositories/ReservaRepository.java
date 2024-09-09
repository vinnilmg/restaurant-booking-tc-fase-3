package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
    Optional<ReservaEntity> findByCpfCliente(String cpf);
}
