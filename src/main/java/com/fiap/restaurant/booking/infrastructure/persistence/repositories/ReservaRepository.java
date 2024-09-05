package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}
