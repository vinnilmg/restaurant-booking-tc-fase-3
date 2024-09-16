package com.fiap.restaurant.booking.infrastructure.persistence.repositories;

import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
    List<RestauranteEntity> findByNome(String nome);

    List<RestauranteEntity> findByTipoCulinaria(String tipoCulinaria);

    List<RestauranteEntity> findByMediaFeedback(Double mediaFeedback);

    Optional<RestauranteEntity> findByCnpj(String cnpj);
}
