package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedBackRepository extends JpaRepository<FeedBackEntity, Long> {

    List<FeedBackEntity> findAllByNomeCliente(String nomeCliente);

    List<FeedBackEntity> findByRestauranteId(Long idRestaurante);
}
